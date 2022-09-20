package fr.radi3nt.networking.network.reader;

public class ReceivedPacketInfo {

    private final CurrentRead currentRead;
    private final int size;

    public ReceivedPacketInfo(int size) {
        this.size = size;
        currentRead = new CurrentRead(new byte[size]);
    }

    public CurrentRead getCurrentRead() {
        return currentRead;
    }

    public int getSize() {
        return size;
    }
}
