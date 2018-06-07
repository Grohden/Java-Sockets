package app.museum.interfaces;

/**
 * All the prompt creatable classes that implements this, must
 * ask from the command line informations about itself and return an instance
 * using (or not) those informations
 */
public interface CommandLineCreatable<T> {
    T onCommandLineRequest();
}
