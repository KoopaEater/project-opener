package search;

public class LevenshteinEditDistanceAlgorithm implements EditDistanceAlgorithm{
    @Override
    public int calcDistance(String a, String b) {
        return lev(a, b);
    }

    // With inspiration from https://en.wikipedia.org/wiki/Levenshtein_distance
    private int lev(String a, String b) {
        if (b.isEmpty()) return a.length();
        if (a.isEmpty()) return b.length();

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
