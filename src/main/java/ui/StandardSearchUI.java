package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StandardSearchUI implements SearchUI {

    private JFrame frame;
    private JTextField input;
    private Robot focusRobot;

    public StandardSearchUI() {

        try {
            focusRobot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


        frame = new JFrame("Project opener");


        input = new JTextField(20);
        frame.add(input);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);


        // Måske virker det???
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);
                SwingUtilities.invokeLater(() -> {
                    input.requestFocusInWindow();
                });
            }
        });

    }

    // Courtesy of "https://namekdev.net/2016/03/regain-focus-once-lost-by-java-swing/"
    private void focusWindow() {
        try {
            // remember the last location of mouse
            Point oldMouseLocation = MouseInfo.getPointerInfo().getLocation();

            // simulate a mouse click on title bar of window
            focusRobot.mouseMove(frame.getX() + 10, frame.getY() + 5);
            focusRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            focusRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // move mouse to old location
            int oldX = (int) Math.round(oldMouseLocation.getX());
            int oldY = (int) Math.round(oldMouseLocation.getY());
            focusRobot.mouseMove(oldX, oldY);
        }
        catch (Exception ex) { }
    }

    @Override
    public void show() {
        frame.setVisible(true);
        focusWindow();
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }
}
