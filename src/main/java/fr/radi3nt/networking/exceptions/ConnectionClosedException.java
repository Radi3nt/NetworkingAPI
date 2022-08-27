package fr.radi3nt.networking.exceptions;

import java.io.IOException;

public class ConnectionClosedException extends NetworkException {

    public ConnectionClosedException() {
        super("Connection closed");
    }
}
