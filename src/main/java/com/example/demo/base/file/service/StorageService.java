package com.example.demo.base.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    public String uploadImageToFileSystem(MultipartFile file,String id) throws IOException;
    public byte[] downloadImageFromFileSystem(String fileName, String id) throws IOException;
}
