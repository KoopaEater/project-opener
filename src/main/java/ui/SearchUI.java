package ui;

import project.Project;

public interface SearchUI {

    void show();
    void hide();
    void reset();

    void setSearchList(Project[] projects);

}
