package file;

import java.util.List;

public interface FileHandler {
    List<String> getProjectNames();
    void reloadFiles();
}
