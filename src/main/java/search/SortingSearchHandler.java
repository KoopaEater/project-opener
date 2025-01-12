package search;

import project.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingSearchHandler implements SearchHandler {
    private final List<Project> projects;
    public SortingSearchHandler(List<Project> projects) {
        this.projects = new ArrayList<>(projects);
        this.projects.sort(Comparator.comparing(Project::getName));
    }
    @Override
    public List<Project> findBestMatches(String query) {
        return projects;
    }
}
