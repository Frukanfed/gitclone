package storage;

import repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class IndexStore {
    private final Repository repository;

    public IndexStore (Repository repository) {
        this.repository = repository;
    }

    public void add(String filePath, String hash) {
        String content = filePath + " " + hash;
        Path indexDir = repository.getIndexDirectory();
        boolean found = false;

        try {
            List<String> lines = Files.readAllLines(indexDir);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                String[] text = line.split(" ");

                if (Objects.equals(filePath, text[0])){
                    lines.set(i, content);
                    found = true;
                }
            }

            if (!found) {
                lines.add(content);
            }

            Files.write(indexDir, lines);
        } catch (IOException e) {
            throw new RuntimeException("Failed to index: " + e.getMessage(), e);
        }

    }
}
