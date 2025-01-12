package project;

import java.util.List;

public interface ProjectFactory {
    List<Project> createProjects(List<String> projectNames);
}
