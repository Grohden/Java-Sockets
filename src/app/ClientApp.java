package app;

import app.console.Menu;
import app.museum.entities.Artwork;
import app.museum.entities.User;
import app.socket.ClientSocketHelper;
import app.socket.SocketHelper;
import app.socket.comunication.client.ClientOption;
import app.socket.comunication.server.ServerResponse;
import app.socket.comunication.server.responses.CollectionResponse;
import app.utils.Tuple;
import app.utils.log.Logger;
import app.utils.log.LoggerType;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientApp {
    private static final String CONNECTION_SUCCESS = "Connection succeeded";
    private static final String CONNECTION_ERROR = "Socket connection error, try again";
    private static final Integer MAXIMUM_CONNECTION_ATTEMPTS = 3;
    private static Tuple<String, Integer> cachedSocketInformation;

    private static final Menu<Runnable> clientMenu = new Menu<>(
            "Client menu, choose what to do",
            Arrays.asList(
                    Tuple.from("Register user", ClientApp::registerUser),
                    Tuple.from("Remove user registry", ClientApp::removeUser),
                    Tuple.from("View all users", ClientApp::requestAllUsers),
                    Tuple.from("View all artworks", ClientApp::requestAllArtworks),
                    Tuple.from("See painting info", () -> {
                    })
            )
    );

    private static Tuple<String, Integer> promptSocketConnection() {
        String ip = Menu.prompt("Inform the server IP");

        Integer portNumber = Menu.promptInt(
                "Inform the port number",
                0,
                Integer.MAX_VALUE
        );

        return Tuple.from(ip, portNumber);
    }

    /**
     * Prompts the user for an IP and a port number and try to connect
     * if it cant connect, try again until it works.
     */
    private static Socket getAndCacheInitialConnection() {
        final Optional<Socket> connection = getConnection();

        if (connection.isPresent()) {
            ClientSocketHelper.sendMessage(connection.get(), ClientOption.HELLO);

            try {
                connection.get().close();
            } catch (IOException ignored) {
            }

            System.out.println(CONNECTION_SUCCESS);
            return connection.get();
        } else {
            return getAndCacheInitialConnection();
        }
    }

    /**
     * Prompts the user for an IP and a port number and try to connect
     * if it cant connect, try again until the count reaches the maximum
     */
    private static Optional<Socket> getConnection(Integer count) {

        if (count >= MAXIMUM_CONNECTION_ATTEMPTS) {
            System.out.println(CONNECTION_ERROR);
            cachedSocketInformation = null;
            return Optional.empty();
        }

        if (cachedSocketInformation == null) {
            cachedSocketInformation = promptSocketConnection();
        }

        Optional<Socket> clientSocket = SocketHelper.tryConnection(cachedSocketInformation);

        return clientSocket.isPresent()
                ? clientSocket
                : getConnection(count + 1);
    }

    private static Optional<Socket> getConnection() {
        return getConnection(0);
    }

    private static <T> void requestCollection(
            ClientOption option,
            Consumer<CollectionResponse<T>> successHandler
    ) {
        getConnection().ifPresent(server -> {

            Optional<ServerResponse> response = ClientSocketHelper.sendMessage(server, option, null);

            Boolean failed = response
                    .map(ServerResponse::isError)
                    .orElse(true);

            if (failed) {
                //FIXME: compiler bug here, do not remove the cast!
                final String errorMessage = (String) response
                        .flatMap(ServerResponse::getMessage)
                        .orElse("Error requesting collection");

                System.out.println(errorMessage);
            } else {
                successHandler.accept(
                        //FIXME: compiler bug here, do not remove the specialization!
                        (CollectionResponse<T>) response.get()
                );
            }
            closeSocket(server);

        });


    }

    private static void requestAllUsers() {
        ClientApp.<User>requestCollection(
                ClientOption.LIST_USERS,
                response -> response
                        .getReturn()
                        .forEach(System.out::println)
        );
    }

    private static void requestAllArtworks() {
        ClientApp.<Artwork>requestCollection(
                ClientOption.LIST_ARTWORKS,
                response -> response
                        .getReturn()
                        .forEach(System.out::println)
        );
    }

    private static void closeSocket(Socket server) {
        try {
            server.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Tries to register a user in the server socket
     */
    private static void registerUser() {
        final User user = new User().requestDataFromConsole();

        getConnection().ifPresent(server -> {
            Optional<ServerResponse> response = ClientSocketHelper.sendMessage(server, ClientOption.ADD_USER_REGISTRY, user);

            Boolean failed = response
                    .map(ServerResponse::isError)
                    .orElse(true);

            if (failed) {
                //FIXME: compiler bug here, do not remove the cast!
                final String errorMessage = (String) response
                        .flatMap(ServerResponse::getMessage)
                        .orElse("Server error sending user");

                System.out.println(errorMessage);
            } else {
                System.out.println("Success!");
            }

            closeSocket(server);
        });
    }

    /**
     * Tries to register a user in the server socket
     */
    private static void removeUser() {
        final String userEmail = Menu.prompt("User email: ");

        getConnection().ifPresent(server -> {
            Optional<ServerResponse> response = ClientSocketHelper.sendMessage(
                    server,
                    ClientOption.REMOVE_USER_REGISTRY,
                    userEmail
            );

            Boolean failed = response
                    .map(ServerResponse::isError)
                    .orElse(true);

            if (failed) {
                //FIXME: compiler bug here, do not remove the cast!
                final String errorMessage = (String) response
                        .flatMap(ServerResponse::getMessage)
                        .orElse("Server error sending user");

                System.out.println(errorMessage);
            } else {
                System.out.println("Success!");
            }

            closeSocket(server);
        });
    }

    /**
     * Boots the client app, asks for connection properties and
     * try to connect
     */
    public static void boot() {
        Logger.setLoggerType(LoggerType.CLIENT);

        Optional<Runnable> chosenOption;
        getAndCacheInitialConnection();

        do {
            chosenOption = clientMenu.spawnMenuWithExit("Exit");

            chosenOption.ifPresent(Runnable::run);

        } while (chosenOption.isPresent());


    }
}
