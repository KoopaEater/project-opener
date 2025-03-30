package initiator;

import db.ProjectTypeDatabase;
import project.Project;
import project.ProjectType;
import ui.ProjectTypeDialog;

import java.io.IOException;

public class TypeOrAskBasedProjectInitiator implements ProjectInitiator {
    private final String directoryPath;
    private final ProjectTypeDialog dialog;
    private ProjectTypeDatabase db;
    public TypeOrAskBasedProjectInitiator(String directoryPath, ProjectTypeDialog dialog, ProjectTypeDatabase db) {
        this.directoryPath  = directoryPath;
        this.dialog = dialog;
        this.db = db;
    }
    @Override
    public boolean openProject(Project project, boolean special) {
        ProjectType projectType = project.getType();
        if (projectType == ProjectType.UNKNOWN || special) {
            projectType = dialog.ask();
            if (projectType == null) { // Probably because you cancelled the dialog
                return false;
            }
            db.put(project.getName(), projectType);
            project.setType(projectType);
        }
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
