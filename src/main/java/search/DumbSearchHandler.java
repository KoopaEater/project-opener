package search;

import java.util.List;

public class DumbSearchHandler implements SearchHandler {
    @Override
    public List<String> findBestMatches(String query, List<String> list) {
        return list;
    }
}
