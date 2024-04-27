package com.example.oauth2authenticationdemo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class InMemoryMultipartFile implements MultipartFile {

    private final String name;
    private final String originalFileName;
    private final byte[] content;

    public InMemoryMultipartFile(String name, String originalFileName, byte[] content) {
        this.name = name;
        this.originalFileName = originalFileName;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFileName;
    }

    @Override
    public String getContentType() {
        return null; // Puteți adăuga implementarea pentru a returna tipul MIME adecvat, dacă este necesar
    }

    @Override
    public boolean isEmpty() {
        return content == null || content.length == 0;
    }

    @Override
    public long getSize() {
        return content.length;
    }

    @Override
    public byte[] getBytes() {
        return content;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        // Implementarea nu este necesară pentru utilizarea acestui obiect MultipartFile în modul în care este utilizat aici
    }
}

