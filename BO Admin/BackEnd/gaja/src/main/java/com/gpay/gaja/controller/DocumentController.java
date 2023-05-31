package com.gpay.gaja.controller;

import com.gpay.gaja.model.domain.Employee;
import com.gpay.gaja.model.repository.EmployeeRepository;
import com.gpay.gaja.service.DocumentService;

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
@RequestMapping("/doc")
public class DocumentController {
    @Autowired
    private DocumentService docService;

    @Autowired
    private EmployeeRepository emplRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload/{employeeId}")
    public void uploadImage(@RequestParam("imageDoc") MultipartFile document, @PathVariable Long employeeId)
            throws IOException {
        docService.uploadImage(document, employeeId);
    }

    @GetMapping("/download/{docName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String docName) {
        byte[] imageDoc = docService.downloadImage(docName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("imageDoc/png")).body(imageDoc);
    }

    @GetMapping("/downloads/{id}/{docName}")
    public ResponseEntity<byte[]> downloadImages(@PathVariable Long id, @PathVariable String docName) {
        Employee findById = emplRepository.findById(id).get();
        byte[] imageDoc = docService.downloadImages(findById, docName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("imageDoc/png")).body(imageDoc);
    }
}
