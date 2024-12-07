package search;

import org.javatuples.LabelValue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModifiedLevenshteinSearchHandler implements SearchHandler {
    @Override
    public List<String> findBestMatches(String query, List<String> list) {
        List<LabelValue<String, Integer>> scores = new ArrayList<>();

        for (String s : list) {
            int score = lev(query, s);
            scores.add(new LabelValue<>(s, score));
        }

        scores.sort(Comparator.comparingInt(LabelValue::getValue));

        return scores.stream().map(score -> score.getLabel()).toList();
    }

    // With inspiration from https://en.wikipedia.org/wiki/Levenshtein_distance
    private int lev(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) return 0; // MODIFICATION BY ME

        char aHead = a.charAt(0);
        char bHead = b.charAt(0);
        String aTail = a.substring(1);
        String bTail = b.substring(1);

        if (aHead == bHead) return lev(aTail, bTail);

        int resA = lev(aTail, b);
        int resB = lev(a, bTail);
        int resC = lev(aTail, bTail);

        return 1 + minOfThree(resA, resB, resC);
    }

    private int minOfThree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
