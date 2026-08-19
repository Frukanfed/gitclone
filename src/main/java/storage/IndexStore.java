package storage;

import repository.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IndexStore {
    private final Repository repository;

    public IndexStore (Repository repository) {
        this.repository = repository;
    }

    private String format(IndexEntry entry) {
        return entry.getPath() + " " + entry.getHash();
    }

    public List<IndexEntry> read() {
        Path indexDir = repository.getIndexDirectory();
        List<IndexEntry> list = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(indexDir);

            for (String line : lines) {
                if (line.isBlank()) {
                    continue;
                }

                String[] text = line.split(" ", 2);
                String path = text[0];
                String hash = text[1];

                list.add(new IndexEntry(path, hash));
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to index: " + e.getMessage(), e);
        }

        return list;
    }

    private void write(List<IndexEntry> entries) {
        List<String> lines = new ArrayList<>();

        for (IndexEntry entry : entries) {
            lines.add(format(entry));
        }

        try {
            Files.write(repository.getIndexDirectory(), lines);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write index", e);
        }
    }

    public void add(String filePath, String hash) {
        boolean found = false;
        List<IndexEntry> entries = read();

        for (int i = 0; i < entries.size(); i++) {
            IndexEntry entry = entries.get(i);

            if (Objects.equals(filePath, entry.getPath())) {
                entries.set(i, new IndexEntry(filePath, hash));
                found = true;
                break;
            }
        }

        if (!found) {
            entries.add(new IndexEntry(filePath, hash));
        }

        write(entries);
    }
}
