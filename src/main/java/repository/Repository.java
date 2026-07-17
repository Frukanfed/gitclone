package repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


public class Repository {
    private static final Path REPOSITORY_PATH = Paths.get(".jgit");

    public boolean exists() {
        return Path.of(REPOSITORY_PATH.toString()).toFile().exists();
    }

    public void init() {
        if (exists()) {
            throw new RuntimeException("Repository already exists");
        } else {
            System.out.println("Repository created");
        }
    }
}
