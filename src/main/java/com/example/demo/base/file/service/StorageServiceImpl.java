package com.example.demo.base.file.service;

import com.example.demo.base.file.model.FileData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class StorageServiceImpl implements StorageService{
    private final String USER_DIRECTORY_PATH= "classpath:/images/user/";

    @Override
    public String uploadImageToFileSystem(MultipartFile file,String id) throws IOException {
        String filePath=USER_DIRECTORY_PATH+file.getOriginalFilename();
        file.transferTo(new File(filePath));
        return null;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        byte[] images = Files.readAllBytes(new File(USER_DIRECTORY_PATH+fileName).toPath());
        return images;
    }

}
