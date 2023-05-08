package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.NgaturLuwh;
import com.gpay.gaja.common.dto.UserDTO;
import com.gpay.gaja.service.UserService;

import lombok.Data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        UserDTO dto = service.save(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAll() {
        List<UserDTO> dto = service.getAll();
        return dto;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        NgaturLuwh test = new NgaturLuwh();
        test.setData(service.login(userDTO));
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
