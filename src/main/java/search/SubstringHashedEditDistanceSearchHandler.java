package search;

import org.javatuples.LabelValue;

import java.util.*;

public class SubstringHashedEditDistanceSearchHandler implements SearchHandler {
    private final int MAX_EDIT_DIST = 20;
    private final List<String> projectNames;
    private final Map<String, Set<LabelValue<String, Integer>>[]> substringMap;
    public SubstringHashedEditDistanceSearchHandler(List<String> projectNames) {
        this.projectNames = projectNames;
        this.substringMap = new HashMap<>();
        preProcess();
        System.out.println(substringMap);
    }

    private void preProcess() {
        for (String projectName : projectNames) {
            addAllSubstringsToMap(projectName);
        }
    }

    private void addAllSubstringsToMap(String str) {
        addAllSubstringsToMap(str, str, 0, new HashSet<>());
    }

    private void addAllSubstringsToMap(String str, String subStr, int dist, Set<String> alreadyAdded) {
        if (alreadyAdded.contains(subStr)) return;

        if (!substringMap.containsKey(subStr)) {
            substringMap.put(subStr, getArrayFilledWithSets());
        }
        Set<LabelValue<String, Integer>> strs = substringMap.get(subStr)[dist];
        strs.add(new LabelValue<>(str, dist));

        if (subStr.length() <= 1) {
            return;
        }

        Set<String> newAlreadyAdded = new HashSet<>(alreadyAdded);
        newAlreadyAdded.add(subStr);

        for (int i = 0; i < subStr.length(); i++) {
            StringBuilder sb = new StringBuilder(subStr);
            sb.deleteCharAt(i);
            String newSubStr = sb.toString();
            addAllSubstringsToMap(str, newSubStr, dist + 1, newAlreadyAdded);
        }
    }

    private Set<LabelValue<String, Integer>>[] getArrayFilledWithSets() {
        Set<LabelValue<String, Integer>>[] array = new Set[MAX_EDIT_DIST+1];
        for (int i = 0; i < array.length; i++) {
            array[i] = new HashSet<>();
        }
        return array;
    }

    @Override
    public List<String> findBestMatches(String query) {
        return null;
    }
}
