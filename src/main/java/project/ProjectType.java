package project;

public enum ProjectType {
    VSCODE("code"),
    INTELLIJ("idea");
    private final String command;
    private ProjectType(String command) {
        this.command = command;
    }
    public String getCommand() {return command;}
}
