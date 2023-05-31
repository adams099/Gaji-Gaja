package com.gpay.gaja.service.impl;

import com.gpay.gaja.model.domain.Document;
import com.gpay.gaja.model.domain.Employee;
import com.gpay.gaja.model.repository.DocumentRepository;
import com.gpay.gaja.model.repository.EmployeeRepository;
import com.gpay.gaja.service.DocumentService;
import com.gpay.gaja.util.ImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository docRepository;

    @Autowired
    private EmployeeRepository emplRepository;

    @Override
    public Document uploadImage(MultipartFile document, Long employeeId) throws IOException {
        Optional<Employee> employeeOptional = emplRepository.findById(employeeId);

        Document imageDoc = new Document();
        imageDoc.setName(document.getOriginalFilename());
        imageDoc.setType(document.getContentType());
        imageDoc.setImageData(ImageUtil.compressImage(document.getBytes()));
        imageDoc.setEmployeeId(employeeOptional.get());

        return docRepository.save(imageDoc);
    }

    @Override
    public byte[] downloadImage(String docName) {
        Optional<Document> imageData = docRepository.findByName(docName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

    @Override
    public byte[] downloadImages(Employee id, String docName) {
        Optional<Document> imageData = docRepository.findByEmployeeIdAndName(id, docName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

}
