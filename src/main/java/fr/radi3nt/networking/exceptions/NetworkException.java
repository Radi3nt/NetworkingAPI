package fr.radi3nt.networking.exceptions;

import java.io.IOException;

public class NetworkException extends Exception {

    public NetworkException(IOException e) {
        super(e);
    }

    public NetworkException(String message) {
        super(message);
    }
}
