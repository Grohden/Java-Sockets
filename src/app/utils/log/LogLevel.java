package app.utils.log;

public enum LogLevel {
    INFO,
    WARNING,
    ERROR,
    VERBOSE;

    private boolean isEnabled = true;

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
