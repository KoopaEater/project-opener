package file;

import java.io.File;

public class OnlyDirectoriesFileFilter implements FileFilter{
    @Override
    public boolean filter(File file) {
        return file.isDirectory();
    }
}
