package storage;

import objects.Blob;
import repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStore {
    private final Repository repository;

    public ObjectStore(Repository repository) {
        this.repository = repository;
    }

    public void save(Blob blob) {
        Path objectPath = repository.getObjectsDirectory().resolve(blob.getHash());

        if (Files.exists(objectPath)) {
            return;
        }

        try {
            Files.write(objectPath, blob.getData());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create object file: " + e.getMessage(), e);
        }
    }
}
