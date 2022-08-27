package fr.radi3nt.networking.connection.handler;

import fr.radi3nt.networking.connection.Connection;
import fr.radi3nt.networking.exceptions.NetworkException;

public interface ServerConnectionHandler {

    Connection accept() throws NetworkException;

}
