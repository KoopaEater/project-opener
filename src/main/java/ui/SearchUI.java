package ui;

import project.Project;

import java.util.List;

public interface SearchUI {

    void show();
    void hide();
    void reset();
    boolean isShown();

    void setSearchList(List<Project> projects);
    void setSearchIndex(int index);
    void selectNextProject();
    void selectPreviousProject();
    Project getSelectedProject();

}
