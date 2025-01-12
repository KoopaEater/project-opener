package search;

import project.Project;

import java.util.List;

public interface SearchHandler {
    List<Project> findBestMatches(String query);
}
