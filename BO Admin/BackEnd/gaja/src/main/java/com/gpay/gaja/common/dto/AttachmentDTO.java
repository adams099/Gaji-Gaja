package com.gpay.gaja.common.dto;

import org.springframework.web.multipart.MultipartFile;

import com.gpay.gaja.model.domain.Company;

import lombok.Data;

@Data
public class AttachmentDTO {

    private Long id;
    private String name;
    private String type;
    private MultipartFile imageData;

    private Long companyId;
    private Company companyz;
}