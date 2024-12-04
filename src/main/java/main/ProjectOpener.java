package main;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;
import ui.SearchUI;
import ui.StandardSearchUI;

public class ProjectOpener {

    private BackgroundInputHandler bgInputHandler;
    private SearchUI ui;

    public ProjectOpener() {

        ui = new StandardSearchUI();

        bgInputHandler = new StandardBackgroundInputHandler(this::onOpenCommand, this::onCloseCommand);

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
}
