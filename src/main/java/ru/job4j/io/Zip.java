package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));

                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateCountArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("You need input root folder "
                    + "and file extension for search");
        }
    }

    public void validateStartDir(Path start) {
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("You need input root folder first argument");
        }
    }

    public String controlExt(String ext) {
        return (ext.charAt(0) != '.') ? "." + ext : ext;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validateCountArgs(args);
        ArgsName argZip = ArgsName.of(args);
        Path start = Paths.get(argZip.get("d"));
        Path output = Paths.get(argZip.get("o"));
        zip.validateStartDir(start);
        String finalExt = zip.controlExt(argZip.get("e"));
        List<Path> sources = search(start, p -> !p.toFile().getName().endsWith(finalExt));
        zip.packFiles(sources, output.toFile());
    }
}
