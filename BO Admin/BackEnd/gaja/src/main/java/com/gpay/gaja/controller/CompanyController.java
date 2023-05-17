package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.CompanyDTO;
import com.gpay.gaja.service.CompanyService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id) {
        CompanyDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CompanyDTO> save(@RequestBody CompanyDTO companyDTO) {
        if (companyDTO.getId() == null || companyDTO.getId() == 0) {
            companyDTO.setCreatedTime(LocalDateTime.now());
        } else {
            companyDTO.setUpdateTime(LocalDateTime.now());
        }
        CompanyDTO dto = service.save(companyDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<CompanyDTO> getAll() {
        List<CompanyDTO> dto = service.getAll();
        return dto;
    }
}
