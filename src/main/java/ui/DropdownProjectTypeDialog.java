package ui;

import project.ProjectType;

import javax.swing.*;

public class DropdownProjectTypeDialog implements ProjectTypeDialog {
    private final JPanel panel;
    private final JComboBox<ProjectType> dropdown;
    public DropdownProjectTypeDialog() {
        panel = new JPanel();
        JLabel text = new JLabel("Which IDE should be used?");
        ProjectType[] types = ProjectType.getAll();
        dropdown = new JComboBox<>(types);
        panel.add(text);
        panel.add(dropdown);
    }
    @Override
    public ProjectType ask() {
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Choose an IDE",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            return (ProjectType) dropdown.getSelectedItem();
        } else {
            return null;
        }
    }
}
