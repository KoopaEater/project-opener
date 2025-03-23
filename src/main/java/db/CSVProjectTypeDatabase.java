package db;

import project.ProjectType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVProjectTypeDatabase implements ProjectTypeDatabase {
    private final String path;
    private final Map<String, ProjectType> typeMap;
    public CSVProjectTypeDatabase(String directoryPath) {
        this.path = directoryPath + "project-types.csv";
        this.typeMap = new HashMap<>();
        handleLoad();
    }
    private void handleLoad() {
        File dbFile = new File(path);
        createFileIfNeeded(dbFile);
        loadIntoMap(dbFile);
        System.out.println(typeMap);
    }
    private void createFileIfNeeded(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadIntoMap(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cells = line.split(",");
                String projectName = cells[0];
                ProjectType projectType = ProjectType.fromString(cells[1].toLowerCase());
                typeMap.put(projectName, projectType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean put(String projectName, ProjectType projectType) {
        return false;
    }

    @Override
    public ProjectType get(String projectName) {
        return null;
    }
}
