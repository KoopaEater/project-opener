package ui;

import project.Project;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.*;

public class StandardSearchUI implements SearchUI {

    private final JFrame frame;
    private final JTextField input;
    private final JList<Project> searchList;
    private final Robot focusRobot;
    private final SearchCommand searchCommand;

    public StandardSearchUI(SearchCommand onSearch) {

        this.searchCommand = onSearch;

        try {
            focusRobot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


        frame = new JFrame("Project opener");
        frame.setUndecorated(true);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        contentPane.add(searchPanel, BorderLayout.CENTER);

        input = new JTextField(20);
        searchPanel.add(input);

        searchList = new JList<Project>();
        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchPanel.add(searchList);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        addFocusListener();
        addSearchListener();

    }

    private void addSearchListener() {

        input.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update(DocumentEvent e) {
                searchCommand.trigger(input.getText());
            }
        });

    }

    private void addFocusListener() {

        // Måske virker det???
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                SwingUtilities.invokeLater(input::requestFocusInWindow);
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
        catch (Exception ex) {
            System.err.println("Could not focus window");
        }
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

    @Override
    public void reset() {
        input.setText("");
    }

    @Override
    public void setSearchList(Project[] projects) {
        searchList.setListData(projects);
        frame.pack();
    }
}
