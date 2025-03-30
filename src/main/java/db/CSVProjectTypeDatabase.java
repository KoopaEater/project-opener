package db;

import project.ProjectType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVProjectTypeDatabase implements ProjectTypeDatabase {
    private final String SEPPERATOR = ";";
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
    }
    private void createFileIfNeeded(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadIntoMap(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(SEPPERATOR);
                String projectName = cells[0];
                ProjectType projectType = ProjectType.fromString(cells[1]);
                typeMap.put(projectName, projectType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean appendToDB(String projectName, ProjectType projectType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(projectName + SEPPERATOR + projectType.toInternalName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private boolean changeTypeInDB(String projectName, ProjectType projectType) {
        List<String> lines  = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(projectName + SEPPERATOR)) {
                    line = projectName + SEPPERATOR + projectType.toInternalName();
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean put(String projectName, ProjectType projectType) {
        boolean status = typeMap.containsKey(projectName) ? changeTypeInDB(projectName, projectType) : appendToDB(projectName, projectType);
        typeMap.put(projectName, projectType);
        return status;
    }

    @Override
    public ProjectType get(String projectName) {
        return typeMap.getOrDefault(projectName, ProjectType.UNKNOWN);
    }
}
