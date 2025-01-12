package search;

import org.javatuples.LabelValue;
import project.Project;

import java.util.*;

public class SubstringHashedEditDistanceSearchHandler implements SearchHandler {
    private final int MAX_WORD_LENGTH = 19;
    private final int MAX_EDIT_DIST = MAX_WORD_LENGTH + 20;
    private final List<String> projectNames;
    private final Map<String, Set<String>[]> substringMap;

    public SubstringHashedEditDistanceSearchHandler(List<String> projectNames) {
        this.projectNames = projectNames;
        this.substringMap = new HashMap<>();
        preProcess();
        System.out.println("DONE PREPROCESSING");
    }

    private void preProcess() {
        for (String projectName : projectNames) {
            System.out.println("BEGINNING: " + projectName);
            addAllSubstringsToMap(projectName);
            System.out.println("ENDING: " + projectName);
        }
    }

    private void addAllSubstringsToMap(String str) {

        int wordLengthDiff = str.length() - MAX_WORD_LENGTH;

        if (wordLengthDiff <= 0) {
            addAllSubstringsToMap(str, str, 0, 0, new HashSet<>());
            return;
        }

        for (int i = wordLengthDiff; i >= 0; i--) {
            String subWord = str.substring(i, i+MAX_WORD_LENGTH);
            System.out.println("BEGINNING SUBWORD: " + subWord);
            addAllSubstringsToMap(str, subWord, wordLengthDiff, 0, new HashSet<>());
            System.out.println("ENDING SUBWORD: " + subWord);
        }

    }

    private void addAllSubstringsToMap(String str, String subStr, int dist, int i, Set<String> alreadyAdded) {
        // i is the next index to do the algorithm from

        // Check if it has already been done (dynamic programming)
        if (alreadyAdded.contains(subStr)) {
            return;
        } else {
            alreadyAdded.add(subStr);
        }

        // Stop if too many edits
        if (dist > MAX_EDIT_DIST) {
            return;
        }

        // Add substring to map
        Set<String>[] editDistances = substringMap.get(subStr);
        if (editDistances == null) {
            Set<String>[] arr = getArrayFilledWithSets();
            arr[dist].add(str);
            substringMap.put(subStr, arr);
        } else {
            editDistances[dist].add(str);
        }

        // Find next substring
        for (int ii = i; ii < subStr.length(); ii++) {
            String newSubStr = subStr.substring(0, ii) + subStr.substring(ii+1);
            addAllSubstringsToMap(str, newSubStr, dist + 1, ii, alreadyAdded);
        }

    }

    private Set<String>[] getArrayFilledWithSets() {
        Set<String>[] array = new Set[MAX_EDIT_DIST+1];
        for (int i = 0; i < array.length; i++) {
            array[i] = new HashSet<>();
        }
        return array;
    }

    @Override
    public List<Project> findBestMatches(String query) {
        return null;
    }
}
