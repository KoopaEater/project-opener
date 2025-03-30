package initiator;

import project.Project;

import java.io.IOException;

public class TypeBasedProjectInitiator implements ProjectInitiator {
    private final String directoryPath;
    public TypeBasedProjectInitiator(String directoryPath) {
        this.directoryPath  = directoryPath;
    }
    @Override
    public boolean openProject(Project project, boolean special) {
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
            ProcessBuilder builder;
            String command = project.getType().toCommand() + " " + directoryPath + project.getName();
            if (isWindows) {
                builder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                throw new RuntimeException("Not implemented for this OS");
            }
            Process process = builder.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
