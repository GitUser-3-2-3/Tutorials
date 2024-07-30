package com.parth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {
    public static void main(String[] args) {
        System.out.println();

        try {
            Path path = Paths.get("MyApp");
            Path path1 = Paths.get("MyApp", "media");
            Path path2 = Paths.get("MyApp", "source");
            Path mySource = Paths.get("MyText.txt");
            Path myMedia = Paths.get("MyMedia.jpeg");

            Files.createDirectory(path);
            Files.createDirectory(path1);
            Files.createDirectory(path2);

            Files.move(mySource, path2.resolve(mySource.getFileName()));
            Files.move(myMedia, path2.resolve(myMedia.getFileName()));
        } catch (IOException exception) {
            System.out.println("Exception: " + exception);
        }
    }
}
