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
        return switch (type.toLowerCase()) {
            case "vscode" -> ProjectType.VSCODE;
            case "intellij" -> ProjectType.INTELLIJ;
            default -> ProjectType.UNKNOWN;
        };
    }
}
