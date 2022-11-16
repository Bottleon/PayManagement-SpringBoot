package com.example.demo.base.file.controller;

import com.example.demo.base.file.service.StorageService;
import com.example.demo.base.file.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class StorageController {



    @Autowired
    private StorageService storageService;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image")MultipartFile file,@PathVariable("id") String id) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam String fileName,@PathVariable String id) throws IOException {

        byte[] imageData = storageService.downloadImageFromFileSystem(fileName,id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
