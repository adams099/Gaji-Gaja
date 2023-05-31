package com.gpay.gaja.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.gpay.gaja.model.domain.Document;
import com.gpay.gaja.model.domain.Employee;

public interface DocumentService {

    Document uploadImage(MultipartFile document, Long employeeId) throws IOException;

    byte[] downloadImage(String docName);

    byte[] downloadImages(Employee id, String docName);

    // List<ImageDTO> GetAll();

}
