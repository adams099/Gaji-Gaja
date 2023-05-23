package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.ApprovalDTO;
import com.gpay.gaja.service.ApprovalService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/approv")
public class ApprovalController {

    @Autowired
    ApprovalService service;

    @PostMapping("/save")
    public ResponseEntity<ApprovalDTO> save(@RequestBody ApprovalDTO approvalDTO) {
        if (approvalDTO.getId() == null) {
            approvalDTO.setCreatedTime(LocalDateTime.now());
        } else {
            approvalDTO.setUpdateTime(LocalDateTime.now());
        }
        ApprovalDTO dto = service.save(approvalDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<ApprovalDTO> dto = service.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
