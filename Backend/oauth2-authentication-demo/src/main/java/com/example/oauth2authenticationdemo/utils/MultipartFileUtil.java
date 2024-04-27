package com.example.oauth2authenticationdemo.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFileUtil {

    // Metoda pentru a salva un MultipartFile pe disc
    public static String saveMultipartFile(MultipartFile multipartFile, String uploadDir) throws IOException, FileNotFoundException {
        // Creează un fișier gol în directorul specificat
        File file = new File(uploadDir + File.separator + multipartFile.getOriginalFilename());

        // Salvează conținutul MultipartFile în fișierul creat
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }

        // Returnează calea către fișierul salvat
        return file.getAbsolutePath();
    }

    // Metoda pentru a crea un nou obiect MultipartFile pe baza căii către un fișier
    public static MultipartFile createMultipartFileFromFile(String filePath) throws IOException {
        // Creează un obiect File pe baza căii către fișier
        File file = new File(filePath);

        // Citește conținutul fișierului și creează un obiect MultipartFile
        byte[] fileContent = FileCopyUtils.copyToByteArray(file);
        return new InMemoryMultipartFile(file.getName(), file.getName(), fileContent);
    }
}