package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.StatusDTO;
import com.gpay.gaja.service.StatusService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        StatusDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<StatusDTO> save(@RequestBody StatusDTO statusDTO) {
        StatusDTO dto = service.save(statusDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<StatusDTO> getAll() {
        List<StatusDTO> dto = service.getAll();
        return dto;
    }
}
