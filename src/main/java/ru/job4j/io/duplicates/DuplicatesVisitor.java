package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> list = new ArrayList<>();
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> listFiles = new ArrayList<>();
        if (!files.containsKey(fp)) {
            listFiles.add(file.toAbsolutePath());
            files.put(fp, listFiles);
        } else {
            files.get(fp).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        for (FileProperty fp : files.keySet()) {
            if (files.get(fp).size() > 1) {
                list.addAll(files.get(fp));
            }
        }
        return list;
    }
}