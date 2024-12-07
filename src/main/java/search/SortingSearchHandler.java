package search;

import java.util.Comparator;
import java.util.List;

public class SortingSearchHandler implements SearchHandler {
    private final List<String> projectNames;
    public SortingSearchHandler(List<String> projectNames) {
        this.projectNames = projectNames.stream().sorted().toList();
    }
    @Override
    public List<String> findBestMatches(String query) {
        return projectNames;
    }
}
