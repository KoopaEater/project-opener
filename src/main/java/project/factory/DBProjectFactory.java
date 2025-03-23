package project.factory;

import db.ProjectTypeDatabase;
import project.Project;
import project.ProjectType;
import project.StandardProject;

import java.util.ArrayList;
import java.util.List;

public class DBProjectFactory implements ProjectFactory {
    private final ProjectTypeDatabase db;
    public DBProjectFactory(ProjectTypeDatabase projectTypeDatabase) {
        this.db = projectTypeDatabase;
    }
    @Override
    public List<Project> createProjects(List<String> projectNames) {
        List<Project> projects = new ArrayList<Project>();

        for (String projectName : projectNames) {
            ProjectType projectType = db.get(projectName);
            projects.add(new StandardProject(projectName, projectType));
        }

        return projects;

    }
}
