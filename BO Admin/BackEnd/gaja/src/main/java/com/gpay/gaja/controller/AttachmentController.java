package com.gpay.gaja.controller;

import com.gpay.gaja.model.domain.Company;
import com.gpay.gaja.model.repository.CompanyRepository;
import com.gpay.gaja.service.AttachmentService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/att")
public class AttachmentController {
    @Autowired
    private AttachmentService imageService;

    @Autowired
    private CompanyRepository repoCom;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload/{companyId}")
    public void uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Long companyId)
            throws IOException {
        imageService.uploadImage(file, companyId);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] image = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @GetMapping("/downloads/{id}/{fileName}")
    public ResponseEntity<byte[]> downloadImages(@PathVariable Long id, @PathVariable String fileName) {
        Company findById = repoCom.findById(id).get();
        byte[] image = imageService.downloadImages(findById, fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
