package com.example.demo.base.file.service;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class StorageServiceImpl implements StorageService{
    private final String USER_DIRECTORY_PATH=new PathMatchingResourcePatternResolver().getResource("static/images/user/").getURL().getPath();

    public StorageServiceImpl() throws IOException {
    }

    @Override
    public String uploadImageToFileSystem(MultipartFile file,String id) throws IOException {
        File directory = new File(USER_DIRECTORY_PATH+id);
        String filePath=directory.getPath()+"\\"+file.getOriginalFilename();
        if(directory.exists())
            FileUtils.cleanDirectory(directory);
        else
            directory.mkdir();
        file.transferTo(new File(filePath));
        return "파일 업로드 : "+filePath;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName, String id) throws IOException {
        return Files.readAllBytes(new File(USER_DIRECTORY_PATH+"\\"+id+"\\"+fileName).toPath());
    }

}
