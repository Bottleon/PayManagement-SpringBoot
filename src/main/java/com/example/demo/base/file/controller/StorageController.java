package com.example.demo.base.file.controller;

import com.example.demo.base.file.service.StorageService;
import com.example.demo.base.file.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;

@RestController
@RequestMapping("/image")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image")MultipartFile file,@RequestParam("id") String id) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
