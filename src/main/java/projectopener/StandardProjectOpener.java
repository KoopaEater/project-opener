package projectopener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import file.FileHandler;
import file.OnlyDirectoriesFileFilter;
import file.StandardFileHandler;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;
import project.DebugDecoratorProjectFactory;
import project.Project;
import project.ProjectFactory;
import project.VSCodeProjectFactory;
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

    public StandardProjectOpener() {

        ui = new StandardSearchUI(this::onSearchCommand);
        bgInputHandler = new StandardBackgroundInputHandler(this::onOpenCommand, this::onCloseCommand);
        fileHandler = new StandardFileHandler("C:\\Users\\maxka\\Projects", new OnlyDirectoriesFileFilter());
//        projectFactory = new VSCodeProjectFactory();
        projectFactory = new DebugDecoratorProjectFactory(new VSCodeProjectFactory());

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

    private void onOpenCommand() {
        ui.reset();
        ui.show();
    }
    private void onCloseCommand() {
        ui.hide();
    }
    private void onSearchCommand(String query) {
        System.out.println("QUERY = " + query);
        List<Project> results = getResultsFromQuery(query);
        ui.setSearchList(results.toArray(new Project[0]));
        System.out.println(results);
    }

    private List<Project> getResultsFromQuery(String query) {
        SearchHandler searchHandler = query.isBlank() ? fastSearchHandler : goodSearchHandler;
        return searchHandler.findBestMatches(query);
    }
}
