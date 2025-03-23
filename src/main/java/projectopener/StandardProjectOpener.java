package projectopener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import file.FileHandler;
import file.OnlyDirectoriesFileFilter;
import file.StandardFileHandler;
import initiator.ProjectInitiator;
import initiator.TypeBasedProjectInitiator;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;
import project.factory.DebugDecoratorProjectFactory;
import project.Project;
import project.factory.ProjectFactory;
import project.factory.VSCodeProjectFactory;
import search.*;
import ui.SearchUI;
import ui.StandardSearchUI;

import java.util.List;

public class StandardProjectOpener {

    private final BackgroundInputHandler bgInputHandler;
    private final SearchUI ui;
    private final FileHandler fileHandler;
    private final SearchHandler goodSearchHandler, fastSearchHandler;
    private final ProjectFactory projectFactory;
    private final ProjectInitiator projectInitiator;

    public StandardProjectOpener() {

        String path = "C:\\Users\\maxka\\Projects\\";
        ui = new StandardSearchUI(this::onSearchCommand);
        bgInputHandler = new StandardBackgroundInputHandler(this::onOpenCommand, this::onCloseCommand, this::onConfirmSearchCommand, this::onUpCommand, this::onDownCommand);
        fileHandler = new StandardFileHandler(path, new OnlyDirectoriesFileFilter());
        projectFactory = new VSCodeProjectFactory();
//        projectFactory = new DebugDecoratorProjectFactory(new VSCodeProjectFactory());
        projectInitiator = new TypeBasedProjectInitiator(path);

        List<String> projectNames = fileHandler.getProjectNames();
        List<Project> projects = projectFactory.createProjects(projectNames);

        System.out.println(projects);

//        goodSearchHandler = new EditDistanceSearchHandler(projects, new LevenshteinEditDistanceAlgorithm());
        goodSearchHandler = new ExactSubstringSearchHandler(projects);
        fastSearchHandler = new SortingSearchHandler(projects);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(bgInputHandler);
    }


    private void onUpCommand() {
        if (ui.isShown()) {
            ui.selectPreviousProject();
        }
    }

    private void onDownCommand() {
        if (ui.isShown()) {
            ui.selectNextProject();
        }
    }

    private void onConfirmSearchCommand() {
        if (ui.isShown()) {
            ui.hide();
            System.out.println(ui.getSelectedProject());
            projectInitiator.openProject(ui.getSelectedProject());
            ui.reset();
        }
    }

    private void onOpenCommand() {
        ui.reset();
        setSearchListFromQuery("");
        ui.show();
    }

    private void onCloseCommand() {
        ui.hide();
    }

    private void onSearchCommand(String query) {
        setSearchListFromQuery(query);
    }

    private void setSearchListFromQuery(String query) {
        List<Project> results = getResultsFromQuery(query);
        ui.setSearchList(results);
        ui.setSearchIndex(0);
    }


    private List<Project> getResultsFromQuery(String query) {
        SearchHandler searchHandler = query.isBlank() ? fastSearchHandler : goodSearchHandler;
        return searchHandler.findBestMatches(query);
    }
}
