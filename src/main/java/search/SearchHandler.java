package search;

import java.util.List;

public interface SearchHandler {
    List<String> findBestMatches(String query);
}
