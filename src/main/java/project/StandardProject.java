package project;

public class StandardProject implements Project{
    private final String name;
    private ProjectType type;
    public StandardProject(String name, ProjectType type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public ProjectType getType() {
        return type;
    }

    @Override
    public void setType(ProjectType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getName();
    }
}
