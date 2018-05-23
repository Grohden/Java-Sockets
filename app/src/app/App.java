package app;

import app.gui.Menu;
import app.gui.Tuple;

import java.util.Arrays;

public class App {
    private static final Menu<Integer> mainMenu = new Menu<>(
            "Main menu",
            Arrays.asList(
                    Tuple.from("Registrar", 1),
                    Tuple.from("Remover registro", 2)
            )

    );

    private static void bootstrap() {
        mainMenu.spawnMenuWithExit("Exit").ifPresent(integer -> {
            System.out.println("Choosen option: " + integer);
            bootstrap();
        });
    }

    public static void main(String[] args) {
        bootstrap();
    }
}
