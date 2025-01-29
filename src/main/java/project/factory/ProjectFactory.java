package project.factory;

import project.Project;

import java.util.List;

public interface ProjectFactory {
    List<Project> createProjects(List<String> projectNames);
}
