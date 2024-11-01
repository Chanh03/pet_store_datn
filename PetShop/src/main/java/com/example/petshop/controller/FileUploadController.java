package com.example.petshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin("*")
@Slf4j
public class FileUploadController {
    private static final String UPLOAD_DIR_PET = System.getProperty("user.dir") + "/src/main/resources/static/images/pet/";
    private static final String UPLOAD_DIR_PRODUCT = System.getProperty("user.dir") + "/src/main/resources/static/images/product/";

    @PostMapping("/pets")
    public ResponseEntity<String> uploadFilePet(@RequestParam("file") MultipartFile file) {
        return uploadFile(file, UPLOAD_DIR_PET);
    }

    @PostMapping("/products")
    public ResponseEntity<String> uploadFileProduct(@RequestParam("file") MultipartFile file) {
        return uploadFile(file, UPLOAD_DIR_PRODUCT);
    }

    private ResponseEntity<String> uploadFile(MultipartFile file, String uploadDirPath) {
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            return new ResponseEntity<>("Could not create upload directory", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            File destinationFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(destinationFile);
            return new ResponseEntity<>("File uploaded successfully: " + destinationFile.getAbsolutePath(), HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error uploading file", e);
            return new ResponseEntity<>("Error uploading file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
