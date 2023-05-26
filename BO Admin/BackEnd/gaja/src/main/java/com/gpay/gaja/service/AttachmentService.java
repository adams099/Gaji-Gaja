package com.gpay.gaja.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.gpay.gaja.model.domain.Attachment;
import com.gpay.gaja.model.domain.Company;

public interface AttachmentService {

    Attachment uploadImage(MultipartFile file, Long idOwner) throws IOException;

    byte[] downloadImage(String fileName);

    byte[] downloadImages(Company id, String fileName);

    // List<ImageDTO> GetAll();

}
