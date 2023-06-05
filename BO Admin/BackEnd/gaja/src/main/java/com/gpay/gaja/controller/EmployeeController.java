package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.EmployeeDTO;
import com.gpay.gaja.service.EmployeeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
        EmployeeDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO employeeDTO) {
        // employeeDTO.setDateBirth(LocalDateTime.parse(employeeDTO.getDateBirth()));
        employeeDTO.setDateBirth(
                LocalDateTime.parse(employeeDTO.getDateBirths(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        employeeDTO.setJoinDate(
                LocalDateTime.parse(employeeDTO.getJoinDates(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        if (employeeDTO.getId() == null || employeeDTO.getId() == 0) {
            employeeDTO.setCreatedTime(LocalDateTime.now());
        } else {
            employeeDTO.setUpdatedTime(LocalDateTime.now());
        }
        EmployeeDTO dto = service.save(employeeDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<EmployeeDTO> dto = service.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}