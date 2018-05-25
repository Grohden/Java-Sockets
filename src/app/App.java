package app;

import app.gui.Menu;
import app.gui.Tuple;

import java.util.Arrays;

public class App {
    private static final Menu<Runnable> initialMenu = new Menu<>(
            "This instance will be",
            Arrays.asList(
                    Tuple.from("A client", ClientApp::boot),
                    Tuple.from("A server", ServerApp::boot)
            )
    );


    public static void main(String[] args) {
        initialMenu
                .spawnMenu()
                .run();
    }
}
