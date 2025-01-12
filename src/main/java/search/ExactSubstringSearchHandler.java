package search;

import project.Project;

import java.util.*;

public class ExactSubstringSearchHandler implements SearchHandler{
    private final Map<String, Set<Project>> substringMap;
    public ExactSubstringSearchHandler(List<Project> projects) {
        substringMap = new HashMap<String,Set<Project>>();
        addAllToMap(projects);
    }

    private void addAllToMap(List<Project> projects) {
        for (Project project : projects) {
            String projectName = project.getName();
            for (int i = 0; i < projectName.length(); i++) {
                for (int j = i + 1; j <= projectName.length(); j++) {
                    String substring = projectName.substring(i, j);
                    addSubstringToMap(substring, project);
                }
            }
        }
    }

    private void addSubstringToMap(String substring, Project project) {
        Set<Project> set = substringMap.computeIfAbsent(substring, k -> new HashSet<Project>());
        set.add(project);
    }

    @Override
    public List<Project> findBestMatches(String query) {
        Set<Project> set = substringMap.get(query);
        if (set == null) {
            return List.of();
        }
        return new ArrayList<>(set);
    }
}
