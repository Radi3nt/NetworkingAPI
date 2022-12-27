package fr.radi3nt.networking.exceptions;

public class ConnectionClosedException extends NetworkException {

    public ConnectionClosedException() {
        super("Connection closed");
    }
}
