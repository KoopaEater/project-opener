package initiator;

import project.Project;

import java.io.IOException;

public class TypeBasedProjectInitiator implements ProjectInitiator {
    private final String directoryPath;
    public TypeBasedProjectInitiator(String directoryPath) {
        this.directoryPath  = directoryPath;
    }
    @Override
    public void openProject(Project project) {
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
            ProcessBuilder builder;
            String command = project.getType().getCommand() + " " + directoryPath + project.getName();
            if (isWindows) {
                builder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                throw new RuntimeException("Not implemented for this OS");
            }
            System.out.println(command);
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
