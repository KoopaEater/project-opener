package search;

import org.javatuples.LabelValue;
import project.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EditDistanceSearchHandler implements SearchHandler {
    private final List<String> projectNames;
    private final EditDistanceAlgorithm algorithm;
    public EditDistanceSearchHandler(List<String> projectNames, EditDistanceAlgorithm algorithm) {
        this.projectNames = projectNames;
        this.algorithm = algorithm;
    }
    @Override
    public List<Project> findBestMatches(String query) {
//        List<LabelValue<String, Integer>> scores = new ArrayList<>();
//
//        for (String s : projectNames) {
//            int score = algorithm.calcDistance(query, s);
//            scores.add(new LabelValue<>(s, score));
//        }
//
//        scores.sort(Comparator.comparingInt(LabelValue::getValue));
//
//        return scores.stream().map(score -> score.getLabel()).toList();

        return List.of();
    }

}
