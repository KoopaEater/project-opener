package search;

import java.util.List;

public class DumbSearchHandler implements SearchHandler {
    private final List<String> projectNames;
    public DumbSearchHandler(List<String> projectNames) {
        this.projectNames = projectNames;
    }
    @Override
    public List<String> findBestMatches(String query) {
        return projectNames;
    }
}
