package fr.radi3nt.networking.network.reader;

public class ReceivedPacketInfo {

    private final int size;

    public ReceivedPacketInfo(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
