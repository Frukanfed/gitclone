package storage;

import repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class IndexStore {
    private final Repository repository;

    public IndexStore (Repository repository) {
        this.repository = repository;
    }

    public void add(String filePath, String hash) {
        String content = filePath + " " + hash + "\n";

        try {
            Files.writeString(
                    repository.getIndexDirectory(),
                    content,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to index: " + e.getMessage(), e);
        }

    }
}
