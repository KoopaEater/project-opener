package project;

public class DebugDecoratorProject implements Project {
    private final Project project;
    public DebugDecoratorProject(Project project) {
        this.project = project;
    }

    @Override
    public String getName() {
        return project.getName();
    }

    @Override
    public ProjectType getType() {
        return project.getType();
    }

    @Override
    public String toString() {
        return getName() + "|" + getType();
    }
}
