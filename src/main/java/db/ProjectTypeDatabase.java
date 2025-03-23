package db;

import project.ProjectType;

public interface ProjectTypeDatabase {
    boolean put(String projectName, ProjectType projectType);
    ProjectType get(String projectName);
}
