package app.museum.interfaces;

import app.socket.comunication.server.responses.CollectionResponse;

/**
 * Everyone that implements this interface should return a collection
 * TODO: a better name (make clear that this is a collection response interface), or a more abstract interface
 */

public interface SocketSendable<T> {

    CollectionResponse<T> transformToResponse();

}
