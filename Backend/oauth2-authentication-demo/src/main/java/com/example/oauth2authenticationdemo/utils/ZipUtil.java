package com.example.oauth2authenticationdemo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static File createZipFile(List<MultipartFile> videos, List<MultipartFile> images, List<MultipartFile> textFiles) throws IOException {
        // Create a temporary directory
        Path tempDir = createTempDirectory();

//        // Save files to temporary directory
        saveFilesToDirectory(videos, tempDir.resolve("videos"));
        saveFilesToDirectory(images, tempDir.resolve("images"));
        saveFilesToDirectory(textFiles, tempDir.resolve("textFiles"));

        // Create the parent directory for the zip file if it doesn't exist
        Path zipParentDir = tempDir.getParent();
        if (!Files.exists(zipParentDir)) {
            Files.createDirectories(zipParentDir);
        }

        // Zip the contents of the temporary directory
        String zipFileName = "files.zip";
        File zipFile = tempDir.resolve(zipFileName).toFile();
        zipDirectory(tempDir.toFile(), zipFile);

        // Delete temporary directory
        //deleteDirectory(tempDir.toFile());

        return zipFile;
    }

    private static Path createTempDirectory() throws IOException {
        return Files.createTempDirectory("temp");
    }

    private static void saveFilesToDirectory(List<MultipartFile> files, Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        for (MultipartFile file : files) {
            Path destFile = directory.resolve(file.getOriginalFilename());
            Files.write(destFile, file.getBytes());
        }
    }

    private static void zipDirectory(File sourceDir, File zipFile) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            Files.walk(sourceDir.toPath())
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDir.toPath().relativize(path).toString());
                        try {
                            zos.putNextEntry(zipEntry);
                            Files.copy(path, zos);
                            zos.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private static void deleteDirectory(File directory) throws IOException {
        Files.walk(directory.toPath())
                .sorted((a, b) -> b.compareTo(a))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
