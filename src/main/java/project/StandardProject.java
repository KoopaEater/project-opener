package project;

public class StandardProject implements Project{
    private String name;
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
    public String toString() {
        return name + "|" + type;
    }
}
