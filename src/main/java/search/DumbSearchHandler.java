package search;

import project.Project;

import java.util.List;

public class DumbSearchHandler implements SearchHandler {
    private final List<Project> projects;
    public DumbSearchHandler(List<Project> projects) {
        this.projects = projects;
    }
    @Override
    public List<Project> findBestMatches(String query) {
        return projects;
    }
}
