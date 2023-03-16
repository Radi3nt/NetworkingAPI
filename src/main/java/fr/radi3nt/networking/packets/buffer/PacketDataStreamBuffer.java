package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketDataStreamBuffer implements WritablePacketBuffer {

    private final DataOutputStream dataOutputStream;
    private final ByteArrayOutputStream byteArrayOutputStream;

    public PacketDataStreamBuffer() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        this.dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    @Override
    public void write(PacketDataSerializerWriter writer) throws EncodeException {
        try {
            writer.write(dataOutputStream);
        } catch (Exception e) {
            throw new EncodeException(e);
        }
    }

    public byte[] array() {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public int size() {
        return byteArrayOutputStream.size();
    }

    public ReadablePacketBuffer toReadablePacketBuffer() {
        byte[] array = byteArrayOutputStream.toByteArray();
        return new PacketDataByteBuffer(new DataInputStream(new ByteArrayInputStream(array)), array.length);
    }
}
