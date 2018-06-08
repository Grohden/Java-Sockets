package app.utils.log;

public enum LoggerType {
    CLIENT,
    SERVER,
    BOTH;

    public boolean typeEnables(LoggerType type) {
        return LoggerType.BOTH == type || this == type;
    }
}
