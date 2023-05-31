package com.gpay.gaja.common.dto;

import org.springframework.web.multipart.MultipartFile;

import com.gpay.gaja.model.domain.Employee;
import lombok.Data;

@Data
public class DocumentDTO {
    private Long id;
    private String name;
    private String type;
    private MultipartFile imageData;

    private Long employeeId;
    private Employee employeeZ;

}
