package fr.radi3nt.networking.main;

import fr.radi3nt.networking.connection.ConnectingConnection;
import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.socket.SocketAddress;
import fr.radi3nt.networking.packets.Packet;
import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.WritablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.serializers.IntReader;
import fr.radi3nt.networking.packets.buffer.serializers.IntWriter;
import fr.radi3nt.networking.protocol.SizedIdPacketProtocol;
import fr.radi3nt.networking.protocol.id.PacketFactory;
import fr.radi3nt.networking.protocol.id.PacketFactoryProtocolIdentification;

import java.util.HashMap;
import java.util.Map;

public class MainNetworkingAPIDemoClient {

    public static final int PORT = 25564;

    public static void main(String[] args) throws NetworkException {
        ConnectingConnection interactiveConnection = createConnection();
        createNecessaryThreads(interactiveConnection);

        TestPacketReadWrite testPacketReadWrite = new TestPacketReadWrite();
        testPacketReadWrite.number = 12381131;

        interactiveConnection.sendPacket(testPacketReadWrite);

        closeAfterXSeconds(interactiveConnection, 10);
    }

    private static void createNecessaryThreads(ConnectingConnection interactiveConnection) {
        Thread thread = new Thread(interactiveConnection::attachListener);
        thread.start();
    }

    private static ConnectingConnection createConnection() throws NetworkException {
        PacketFactoryProtocolIdentification packetFactoryProtocolIdentification = createIdentification();
        SizedIdPacketProtocol simplePacketProtocol = new SizedIdPacketProtocol(packetFactoryProtocolIdentification);
        return new ConnectingConnection(new SocketAddress("localhost", PORT), simplePacketProtocol, System.out::println);
    }

    public static PacketFactoryProtocolIdentification createIdentification() {
        Map<Integer, PacketFactory> packetFactoryMap = new HashMap<>();
        packetFactoryMap.put(1, new TestPacketFactory());

        return new PacketFactoryProtocolIdentification(packetFactoryMap);
    }

    public static class TestPacketReadWrite implements PacketRead, PacketWrite {

        public int number;

        @Override
        public void read(ReadablePacketBuffer packetBuffer) {
            IntReader intReader = new IntReader();
            packetBuffer.read(intReader);

            number = intReader.getIntResult();
        }

        @Override
        public void write(WritablePacketBuffer packetBuffer) throws EncodeException {
            IntWriter intWriter = new IntWriter(number);
            packetBuffer.write(intWriter);
        }

        @Override
        public String toString() {
            return "TestPacketReadWrite{" +
                    "number=" + number +
                    '}';
        }
    }

    private static class TestPacketFactory implements PacketFactory {
        @Override
        public boolean isPacket(Packet packet) {
            return packet instanceof TestPacketReadWrite;
        }

        @Override
        public PacketRead create() {
            return new TestPacketReadWrite();
        }
    }

    private static void closeAfterXSeconds(ConnectingConnection interactiveConnection, int seconds) throws NetworkException {
        try {
            Thread.sleep(seconds*1_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        interactiveConnection.close();
    }
}
