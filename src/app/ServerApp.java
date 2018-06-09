package app;

import app.museum.entities.Artwork;
import app.museum.entities.Painting;
import app.museum.entities.person.Author;
import app.museum.entities.person.User;
import app.museum.storage.types.ArtworkStorage;
import app.museum.storage.types.AuthorStorage;
import app.museum.storage.types.UserStorage;
import app.socket.ServerSocketHelper;
import app.socket.SocketHelper;
import app.socket.comunication.client.ClientOption;
import app.socket.comunication.server.ServerResponse;
import app.socket.comunication.server.responses.SimpleError;
import app.socket.comunication.server.responses.SimpleSuccess;
import app.utils.Tuple;
import app.utils.log.LogLevel;
import app.utils.log.Logger;
import app.utils.log.LoggerType;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private static final Integer DEFAULT_PORT = 0;
    private static final Integer MAX_RESPONSE_TRY = 3;
    private static final String MAX_RESPONSE_TRY_ERROR = "Error sending response to client";
    private static final String WAITING_CONNECTION = "Waiting for client, connect at ip %s, port %d";
    private static final String CONNECTION_SUCCESS = "Client connected successfully, waiting for message.";
    private static final String SOCKET_ERROR = "Socket error, exiting.";
    private static final String EMAIL_ALREADY_IN_USE = "This email is already being used, try another.";
    private static final String OPERATION_NOT_SUPPORTED = "Operation not supported";

    private static ServerResponse doRegister(User newUser) {
        final boolean success = UserStorage.get().store(newUser);

        return success
                ? new SimpleSuccess("Success")
                : new SimpleError(EMAIL_ALREADY_IN_USE);
    }

    private static ServerResponse processAction(Tuple tuple) {
        ClientOption ac = (ClientOption) tuple.getLeft();

        switch (ac) {
            case ADD_USER_REGISTRY:
                return doRegister((User) tuple.getRight());
            case REMOVE_USER_REGISTRY:
                return removeUserByEmail((String) tuple.getRight());
            case LIST_ARTWORKS:
                return ArtworkStorage.get().transformToResponse();
            case LIST_USERS:
                return UserStorage.get().transformToResponse();
            case HELLO:
                return new SimpleSuccess("World!");
            default:
                return new SimpleError(OPERATION_NOT_SUPPORTED);
        }
    }

    private static ServerResponse removeUserByEmail(String email) {
        final Boolean success = UserStorage.get().remove(user -> user.getEmail().equals(email));

        if (success) {

            return new SimpleSuccess("User removed");
        } else {
            return new SimpleError("Error! User with email '" + email + "' not found");
        }

    }

    private static void sendResponse(Socket server, ServerResponse response, Integer tryCount) {
        if (tryCount >= MAX_RESPONSE_TRY) {
            System.out.println(MAX_RESPONSE_TRY_ERROR);
            return;
        }

        try {
            ServerSocketHelper.sendResponse(server, response);
        } catch (IOException e) {

            new Logger(LoggerType.SERVER).error(e::printStackTrace);

            sendResponse(server, response, tryCount + 1);
        }
    }

    private static void sendResponse(Socket server, ServerResponse response) {
        sendResponse(server, response, 0);
    }

    private static void initializeStorages() {
        final Author leonardo = new Author("Leonardo da Vinci");
        final Author vanGogh = new Author("Vincent van Gogh");

        AuthorStorage.get().storeAll(
                leonardo,
                vanGogh
        );

        final Artwork monaLisa = new Painting()
                .setArtName("Mona Lisa")
                .setAuthor(leonardo)
                .setPaintCurrentLocation("Museu do Louvre")
                .setCreationYear(1503);

        final Artwork noiteEstrelada = new Painting()
                .setArtName("A Noite Estrelada")
                .setAuthor(vanGogh)
                .setPaintCurrentLocation("Museu de Arte Moderna")
                .setCreationYear(1889);

        final Artwork autoRetrato = new Painting()
                .setArtName("Auto-Retrato")
                .setAuthor(vanGogh)
                .setPaintCurrentLocation("Museu de Orsay")
                .setCreationYear(1889);

        ArtworkStorage.get().storeAll(
                monaLisa,
                noiteEstrelada,
                autoRetrato
        );
    }

    public static void boot() {
        Logger.setLoggerType(LoggerType.SERVER);
        LogLevel.ERROR.setEnabled(false);

        initializeStorages();

        try {
            final ServerSocket server = new ServerSocket(DEFAULT_PORT);
            final InetAddress localHost = InetAddress.getLocalHost();

            while (true) {

                System.out.println(String.format(
                        WAITING_CONNECTION,
                        localHost.getHostAddress(),
                        server.getLocalPort()
                ));

                final Socket client = server.accept();
                System.out.println(CONNECTION_SUCCESS);

                SocketHelper
                        .getObjectMessage(client, Tuple.class)
                        .ifPresent(t ->
                                sendResponse(client, processAction(t))
                        );

                client.close();
            }

        } catch (IOException e) {
            System.out.println(SOCKET_ERROR);
        }

    }
}
