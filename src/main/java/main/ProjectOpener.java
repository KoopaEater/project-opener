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

        bgInputHandler = new StandardBackgroundInputHandler(() -> {
            System.out.println("HEJ!");
            ui.show();
        });

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
}
