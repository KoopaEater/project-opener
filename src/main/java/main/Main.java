package main;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import input.background.BackgroundInputHandler;
import input.background.StandardBackgroundInputHandler;

public class Main {

    public static void main(String[] args) {

        BackgroundInputHandler backgroundInputHandler = new StandardBackgroundInputHandler(() -> {
            System.out.println("HEJ!");
        });

        try {
            GlobalScreen.registerNativeHook();
        }
		catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(backgroundInputHandler);

    }

}
