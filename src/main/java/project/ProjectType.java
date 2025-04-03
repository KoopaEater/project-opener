package project;

import java.util.Arrays;

public enum ProjectType {
    VSCODE("VS Code", "code", "vscode"),
    INTELLIJ("IntelliJ", "idea", "intellij"),
    PYCHARM("PyCharm", "pycharm", "pycharm"),
    CLION("CLion", "clion", "clion"),
    WEBSTORM("WebStorm", "webstorm", "webstorm"),
    UNKNOWN("Unknown", "unknown", "unknown");

    private final String command;
    private final String displayName;
    private final String internalName;
    ProjectType(String displayName, String command, String internalName) {
        this.command = command;
        this.displayName = displayName;
        this.internalName = internalName;
    }
    @Override
    public String toString() {return displayName;}
    public String toCommand() {return command;}
    public String toDisplayName() {return displayName;}
    public String toInternalName() {return internalName;}
    public static ProjectType fromString(String type) {
        for (ProjectType pt : getAll()) {
            if (type.equalsIgnoreCase(pt.internalName)) {
                return pt;
            }
        }
        return UNKNOWN;
    }
    public static ProjectType[] getAll() {
        ProjectType[] values = values();
        return Arrays.copyOfRange(values, 0, values.length - 1);
    }
}
