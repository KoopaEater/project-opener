package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardFileHandler implements FileHandler {

    private final String path;
    private File[] files;

    public StandardFileHandler() {
        this.path = "E:\\Programmering\\Hjemmesider";
        reloadFiles();
    }


    @Override
    public List<String> getProjectNames() {
        return Arrays.stream(files).parallel().map(f -> f.getName()).toList();
    }

    @Override
    public void reloadFiles() {
        File directory = new File(path);
        files = directory.listFiles();
    }
}
