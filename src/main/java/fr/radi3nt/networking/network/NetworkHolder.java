package fr.radi3nt.networking.network;

import fr.radi3nt.networking.exceptions.NetworkException;

public interface NetworkHolder {

    void start() throws NetworkException;
    void stop() throws NetworkException;

    NetworkTunnel getTunnel();

    boolean isClosed();
    boolean isOpened();
}
