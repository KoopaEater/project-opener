package project;

public enum ProjectType {
    VSCODE("code"),
    INTELLIJ("idea"),
    UNKNOWN("unknown");

    private final String command;
    ProjectType(String command) {
        this.command = command;
    }
    public String getCommand() {return command;}
    public static ProjectType fromString(String type) {
        switch (type.toLowerCase()) {
            case "vscode":
                return ProjectType.VSCODE;
            case "intellij":
                return ProjectType.INTELLIJ;
            default:
                return ProjectType.UNKNOWN;
        }
    }
}
