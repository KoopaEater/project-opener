package projectopener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import file.FileHandler;
import file.StandardFileHandler;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;
import search.ModifiedLevenshteinSearchHandler;
import search.SearchHandler;
import search.SortingSearchHandler;
import ui.SearchUI;
import ui.StandardSearchUI;

import java.util.List;

public class StandardProjectOpener {

    private final BackgroundInputHandler bgInputHandler;
    private final SearchUI ui;
    private final FileHandler fileHandler;
    private final SearchHandler goodSearchHandler, fastSearchHandler;

    public StandardProjectOpener() {

        ui = new StandardSearchUI(this::onSearchCommand);
        bgInputHandler = new StandardBackgroundInputHandler(this::onOpenCommand, this::onCloseCommand);
        fileHandler = new StandardFileHandler();
        goodSearchHandler = new ModifiedLevenshteinSearchHandler();
        fastSearchHandler = new SortingSearchHandler();

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
        List<String> results = getResultsFromQuery(query);
        System.out.println(results);
    }

    private List<String> getResultsFromQuery(String query) {
        List<String> projects = fileHandler.getProjectNames();
        SearchHandler searchHandler = query.isBlank() ? fastSearchHandler : goodSearchHandler;
        return searchHandler.findBestMatches(query, projects);
    }
}
