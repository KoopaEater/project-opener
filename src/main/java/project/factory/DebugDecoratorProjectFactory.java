package project.factory;

import project.DebugDecoratorProject;
import project.Project;

import java.util.ArrayList;
import java.util.List;

public class DebugDecoratorProjectFactory implements ProjectFactory {
    private final ProjectFactory projectFactory;
    public DebugDecoratorProjectFactory(ProjectFactory projectFactory) {
        this.projectFactory = projectFactory;
    }


    @Override
    public List<Project> createProjects(List<String> projectNames) {
        List<Project> projects = projectFactory.createProjects(projectNames);
        List<Project> debugProjects = new ArrayList<>();
        for (Project project : projects) {
            debugProjects.add(new DebugDecoratorProject(project));
        }
        return debugProjects;
    }
}
