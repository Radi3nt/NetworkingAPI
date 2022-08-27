package fr.radi3nt.networking.exceptions;

import java.io.IOException;

public class EncodeException extends Exception {

    public EncodeException(IOException e) {
        super(e);
    }

}
