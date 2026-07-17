package repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;


public class Repository {
    private static final Path REPOSITORY_PATH = Path.of(".jgit");

    public boolean exists() {
        return Files.exists(REPOSITORY_PATH);
    }

    public void init() {
        if (exists()) {
            throw new RuntimeException("Repository already exists");
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
}
