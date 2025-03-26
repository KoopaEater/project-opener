package projectopener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import db.CSVProjectTypeDatabase;
import db.ProjectTypeDatabase;
import file.FileHandler;
import file.OnlyDirectoriesFileFilter;
import file.StandardFileHandler;
import initiator.ProjectInitiator;
import initiator.TypeBasedProjectInitiator;
import initiator.TypeOrAskBasedProjectInitiator;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;
import project.Project;
import project.ProjectType;
import project.factory.DBProjectFactory;
import project.factory.DebugDecoratorProjectFactory;
import project.factory.ProjectFactory;
import project.factory.OneTypeProjectFactory;
import search.*;
import ui.DropdownProjectTypeDialog;
import ui.ProjectTypeDialog;
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
    private final ProjectTypeDatabase projectTypeDatabase;
    private final ProjectTypeDialog projectTypeDialog;

    public StandardProjectOpener() {

        String path = "C:\\Users\\maxka\\Projects\\";
        ui = new StandardSearchUI(this::onSearchCommand);
        bgInputHandler = new StandardBackgroundInputHandler(this::onOpenCommand, this::onCloseCommand, this::onConfirmSearchCommand, this::onUpCommand, this::onDownCommand);
        fileHandler = new StandardFileHandler(path, new OnlyDirectoriesFileFilter());
        projectTypeDatabase = new CSVProjectTypeDatabase(path);
        projectFactory = new DebugDecoratorProjectFactory(new DBProjectFactory(projectTypeDatabase));
        projectTypeDialog = new DropdownProjectTypeDialog();
        projectInitiator = new TypeOrAskBasedProjectInitiator(path, projectTypeDialog, projectTypeDatabase);

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

    private void onConfirmSearchCommand(boolean special) {
        if (ui.isShown()) {
            ui.hide();
            Project selectedProject = ui.getSelectedProject();
            System.out.println(selectedProject);
            boolean succes = projectInitiator.openProject(selectedProject, special);
            if (succes) {
                ui.reset();
            }
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
