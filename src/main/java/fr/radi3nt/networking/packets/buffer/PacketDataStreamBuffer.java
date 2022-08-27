package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerWriter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

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
        } catch (IOException e) {
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
        ByteBuffer buffer = ByteBuffer.wrap(array);
        buffer.limit(array.length);

        return new PacketDataByteBuffer(buffer);
    }
}
