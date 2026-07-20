package objects;

import util.Hashing;

import java.util.Arrays;

public class Blob {
    private final byte[] data;
    private final String hash;

    public Blob(byte[] data) {
        this.data = Arrays.copyOf(data, data.length);

        this.hash = Hashing.sha1(data);
    }

    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public String getHash() {
        return hash;
    }
}
