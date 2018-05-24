package app.gui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Adapted from this gist: https://gist.github.com/Superpat/619aa7033e416796876e
 * <br />
 * Menu api for the command line
 * <p>
 * Takes a list of options containing a value and a menu title, when called, the menu returns an optional type containing the option.
 * </p>
 *
 * @author Patrick Marchand <mail@patrickmarchand.com>
 * @version 0.3
 * @since 2018-05-23
 */
public class Menu<R> {
    private static final String INVALID_CHOICE = "A sua escolha não é valida";

    /**
     * Title of the menu
     */
    private final String title;

    /**
     * Optional description of the menu
     */
    private final String description;

    /**
     * Collection containing all possible actions
     */
    private final List<Tuple<String, R>> options;

    /**
     * @param title   Title of the menu
     * @param options A list of all possible options
     */
    public Menu(
            final String title,
            final List<Tuple<String, R>> options
    ) {
        this.title = title;
        this.description = null;
        this.options = options;
    }

    /**
     * @param title       Title of the menu
     * @param description Description of the menu
     * @param options     A list of all possible options
     */
    public Menu(
            final String title,
            final String description,
            final List<Tuple<String, R>> options
    ) {
        this.title = title;
        this.description = description;
        this.options = options;
    }

    /**
     * @return Title of the menu
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return Description of the menu
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    /**
     * @return List of the options
     */
    public List<Tuple<String, R>> getOptions() {
        return this.options;
    }

    /**
     * Prints the menu to standard output and captures the users choice
     *
     * @return A callable action
     */
    public R spawnMenu() {
        final Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println('\n' + this.title);
        getDescription().ifPresent(System.out::println);

        do {
            for (int i = 0; i < getOptions().size(); i++) {
                System.out.println((i + 1) + " - " + getOptions().get(i).getLeft());
            }

            if (sc.hasNextInt())
                choice = sc.nextInt();

            if (choice > 0 && (choice - 1) < getOptions().size())
                return getOptions().get(choice - 1).getRight();
            else
                System.out.println(INVALID_CHOICE);

        } while (true);
    }

    /**
     * Prints the menu to standard output and captures the users choice
     *
     * @param exitOption The string used for the exit menu item
     * @return A callable action
     */
    public Optional<R> spawnMenuWithExit(String exitOption) {
        final Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println('\n' + getTitle());
        getDescription().ifPresent(System.out::println);
        System.out.println();

        do {

            for (int i = 0; i < getOptions().size(); i++) {
                System.out.println((i + 1) + " - " + getOptions().get(i).getLeft());
            }

            System.out.println(getOptions().size() + 1 + " - " + exitOption);

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            }

            if (choice > 0 && (choice - 1) < getOptions().size()) {
                return Optional.of(getOptions().get(choice - 1).getRight());
            } else if (choice < 1 || choice > getOptions().size() + 1) {
                System.out.println(INVALID_CHOICE);
            } else {
                return Optional.empty();
            }

        } while (true);
    }

    /**
     * A prompt that takes input from standard input
     *
     * @param question A question presented to the user
     * @return A string answer
     */
    public static String prompt(String question) {
        final Scanner sc = new Scanner(System.in);
        String input = "";

        do {
            System.out.println();
            System.out.println(question);
            input = sc.nextLine();
        } while (input.equals(""));

        return input;
    }

    /**
     * A prompt that takes input from standard input
     *
     * @param question A question presented to the user
     * @return An integer answer
     */
    public static int promptInt(String question, int min, int max) {
        final Scanner sc = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println();
            System.out.println(question);

            if (sc.hasNextInt())
                input = sc.nextInt();

        } while (input < min || input > max);

        return input;
    }

    /**
     * A prompt that takes input from standard input
     *
     * @param question A question presented to the user
     * @return An integer answer
     */
    public static int promptInt(String question) {
        return promptInt(question, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * A prompt that takes input from standard input
     *
     * @param question A question presented to the user
     * @return A double
     */
    public static double promptDouble(String question, double min, double max) {
        final Scanner sc = new Scanner(System.in);
        double input = 0;

        do {
            System.out.println();
            System.out.println(question);

            if (sc.hasNextDouble())
                input = sc.nextDouble();

        } while (input < min || input > max);

        return input;
    }
}