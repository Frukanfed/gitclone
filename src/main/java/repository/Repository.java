package repository;

import objects.Blob;
import storage.IndexStore;
import storage.ObjectStore;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


public class Repository {
    private static final Path REPOSITORY_PATH = Path.of(".jgit");

    public boolean exists() {
        return Files.exists(REPOSITORY_PATH);
    }

    public Path getObjectsDirectory() {
        return REPOSITORY_PATH.resolve("objects");
    }

    public Path getIndexDirectory() {
        return REPOSITORY_PATH.resolve("index");
    }

    public void init() {
        if (exists()) {
            System.err.println("Repository already exists");
            return;
        }

        try {
            Files.createDirectory(REPOSITORY_PATH);

            Files.createDirectory(REPOSITORY_PATH.resolve("objects"));
            Files.createDirectories(REPOSITORY_PATH.resolve("refs/heads/main"));

            Files.writeString(REPOSITORY_PATH.resolve("HEAD"), "ref: refs/heads/main");
            Files.createFile(REPOSITORY_PATH.resolve("index"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize " + e.getMessage(), e);
        }
    }

    public void add(String filePath) {
        if(!exists()) {
            System.err.println("This is not a git repository!");
            return;
        }

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            System.err.println("File does not exist: " + filePath);
            return;
        }

        ObjectStore store = new ObjectStore(this);
        IndexStore index = new IndexStore(this);

        try {
            byte[] data = Files.readAllBytes(path);

            Blob blob = new Blob(data);
            store.save(blob);

            index.add(filePath, blob.getHash());
        } catch (IOException e) {
            throw new RuntimeException("Failed to add " + e.getMessage(), e);
        }

    }
}
