package initiator;

import project.Project;
import project.ProjectType;
import ui.ProjectTypeDialog;

import java.io.IOException;

public class TypeOrAskBasedProjectInitiator implements ProjectInitiator {
    private final String directoryPath;
    private final ProjectTypeDialog dialog;
    public TypeOrAskBasedProjectInitiator(String directoryPath, ProjectTypeDialog dialog) {
        this.directoryPath  = directoryPath;
        this.dialog = dialog;
    }
    @Override
    public void openProject(Project project) {
        ProjectType projectType = project.getType();
        if (projectType == ProjectType.UNKNOWN) {
            projectType = dialog.ask();
        }
        // TODO: FORTSÆT HER! Lav denne funktion om til boolean og returner true, hvis det lykkes at åbne,
        // TODO: og returner false, hvis man canceller eller der opstår fejl.
        // TODO: HUSK OGSÅ AT TILFØJE DET TIL DATABASEN!
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
            ProcessBuilder builder;
            String command = project.getType().toCommand() + " " + directoryPath + project.getName();
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
