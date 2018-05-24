package app;

import app.gui.Menu;
import app.gui.Tuple;

import java.util.Arrays;

public class App {
    private static final Menu<Runnable> initialMenu = new Menu<>(
            "Esta instancia sera:",
            Arrays.asList(
                    Tuple.from("Cliente", ClientApp::boot),
                    Tuple.from("Servidor", ServerApp::boot)
            )
    );


    public static void main(String[] args) {
        initialMenu
                .spawnMenu()
                .run();
    }
}
