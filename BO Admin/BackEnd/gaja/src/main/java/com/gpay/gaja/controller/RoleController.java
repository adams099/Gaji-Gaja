package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.RoleDTO;
import com.gpay.gaja.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        RoleDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO roleDTO) {
        RoleDTO dto = service.save(roleDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<RoleDTO> getAll() {
        List<RoleDTO> dto = service.getAll();
        return dto;
    }
}
