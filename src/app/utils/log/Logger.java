package app.utils.log;

public class Logger {
    private static LoggerType loggerType = LoggerType.BOTH;
    private final LoggerType instanceLogType;

    public static void setLoggerType(LoggerType type) {

    }

    public Logger(LoggerType loggerType) {
        this.instanceLogType = loggerType;
    }

    public void info(Runnable messageSupplier) {
        if (loggerType.typeEnables(instanceLogType) && LogLevel.INFO.isEnabled()) {
            messageSupplier.run();
        }
    }

    public void debug(Runnable messageSupplier) {
        if (loggerType.typeEnables(instanceLogType) && LogLevel.WARNING.isEnabled()) {
            messageSupplier.run();
        }
    }

    public void warning(Runnable messageSupplier) {
        if (loggerType.typeEnables(instanceLogType) && LogLevel.ERROR.isEnabled()) {
            messageSupplier.run();
        }
    }

    public void error(Runnable messageSupplier) {
        if (loggerType.typeEnables(instanceLogType) && LogLevel.ERROR.isEnabled()) {
            messageSupplier.run();
        }
    }
}
