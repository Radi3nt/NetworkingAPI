package fr.radi3nt.networking.network.reader;

import java.io.IOException;
import java.io.InputStream;

public class CurrentRead {

    private final byte[] bytes;
    private int currentRead = 0;

    public CurrentRead(byte[] bytes) {
        this.bytes = bytes;
    }

    public void nonBlockingRead(InputStream stream) throws IOException {
        int available = stream.available();
        int localReadBytes = 0;
        while (localReadBytes<available && unfinished()) {
            int readBytes = stream.read(bytes, currentRead, Math.min(available, bytes.length) - currentRead);
            currentRead += readBytes;
            localReadBytes+=readBytes;
        }
    }

    public void blockingRead(InputStream stream) throws IOException {
        while (unfinished()) {
            currentRead += stream.read(bytes, currentRead,  bytes.length - currentRead);
        }
    }

    public boolean unfinished() {
        return currentRead != bytes.length;
    }

    public byte[] bytes() {
        return bytes;
    }
}
