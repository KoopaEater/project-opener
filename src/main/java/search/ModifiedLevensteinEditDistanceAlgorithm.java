package search;

public class ModifiedLevensteinEditDistanceAlgorithm implements EditDistanceAlgorithm {
    @Override
    public int calcDistance(String a, String b) {
        return modLev(a, b);
    }

    // With inspiration from https://en.wikipedia.org/wiki/Levenshtein_distance
    private int modLev(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) return 0; // MODIFICATION BY ME

        char aHead = a.charAt(0);
        char bHead = b.charAt(0);
        String aTail = a.substring(1);
        String bTail = b.substring(1);

        if (aHead == bHead) return modLev(aTail, bTail);

        int resA = modLev(aTail, b);
        int resB = modLev(a, bTail);
        int resC = modLev(aTail, bTail);

        return 1 + minOfThree(resA, resB, resC);
    }

    private int minOfThree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
