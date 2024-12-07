package search;

import java.util.Comparator;
import java.util.List;

public class SortingSearchHandler implements SearchHandler {
    @Override
    public List<String> findBestMatches(String query, List<String> list) {
        return list.stream().sorted().toList();
    }
}
