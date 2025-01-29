package project.factory;

import project.Project;
import project.ProjectType;
import project.StandardProject;

import java.util.ArrayList;
import java.util.List;

public class VSCodeProjectFactory implements ProjectFactory {
    @Override
    public List<Project> createProjects(List<String> projectNames) {
        List<Project> projects = new ArrayList<Project>();

        for (String projectName : projectNames) {
            projects.add(new StandardProject(projectName, ProjectType.VSCODE));
        }

        return projects;
    }
}
