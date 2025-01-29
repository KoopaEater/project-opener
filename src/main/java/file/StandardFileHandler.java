package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardFileHandler implements FileHandler {

    private final String path;
    private final FileFilter filter;
    private File[] files;

    public StandardFileHandler(String path, FileFilter filter) {
        this.path = path;
        this.filter = filter;
        reloadFiles();
    }


    @Override
    public List<String> getProjectNames() {
        return Arrays.stream(files).parallel().filter(filter::filter).map(File::getName).toList();
    }

    @Override
    public void reloadFiles() {
        File directory = new File(path);
        files = directory.listFiles();
    }
}
