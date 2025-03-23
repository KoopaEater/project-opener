package project;

import java.util.Arrays;

public enum ProjectType {
    VSCODE("VS Code", "code"),
    INTELLIJ("IntelliJ", "idea"),
    UNKNOWN("Unknown", "unknown");

    private final String command;
    private final String displayName;
    ProjectType(String displayName, String command) {
        this.command = command;
        this.displayName = displayName;
    }
    @Override
    public String toString() {return displayName;}
    public String toCommand() {return command;}
    public String toDisplayName() {return displayName;}
    public static ProjectType fromString(String type) {
        return switch (type.toLowerCase()) {
            case "vscode" -> ProjectType.VSCODE;
            case "intellij" -> ProjectType.INTELLIJ;
            default -> ProjectType.UNKNOWN;
        };
    }
    public static ProjectType[] getAll() {
        ProjectType[] values = values();
        return Arrays.copyOfRange(values, 0, values.length - 1);
    }
}
