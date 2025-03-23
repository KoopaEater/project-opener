package project.factory;

import project.Project;
import project.ProjectType;
import project.StandardProject;

import java.util.ArrayList;
import java.util.List;

public class OneTypeProjectFactory implements ProjectFactory {
    private final ProjectType projectType;
    public OneTypeProjectFactory(ProjectType projectType) {
        this.projectType = projectType;
    }
    @Override
    public List<Project> createProjects(List<String> projectNames) {
        List<Project> projects = new ArrayList<Project>();

        for (String projectName : projectNames) {
            projects.add(new StandardProject(projectName, projectType));
        }

        return projects;
    }
}
