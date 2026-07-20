package storage;

import objects.Blob;
import objects.GitObject;
import repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStore {
    private final Repository repository;

    public ObjectStore(Repository repository) {
        this.repository = repository;
    }

    public void save(GitObject gitObject) {
        Path objectPath = repository.getObjectsDirectory().resolve(gitObject.getHash());

        if (Files.exists(objectPath)) {
            return;
        }

        try {
            Files.write(objectPath, gitObject.getData());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create object file: " + e.getMessage(), e);
        }
    }
}
