package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.NgaturLuwh;
import com.gpay.gaja.common.dto.UserDTO;
import com.gpay.gaja.config.SafetyConfiguration;
import com.gpay.gaja.service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<UserDTO> cariEmail(@RequestBody UserDTO userDTO) {
        try {
            service.login(userDTO);
            return new ResponseEntity<UserDTO>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/username")
    public ResponseEntity<UserDTO> cariUsername(@RequestBody UserDTO userDTO) {
        try {
            service.findByUsername(userDTO.getUsername());
            return new ResponseEntity<UserDTO>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        userDTO.setCreated(LocalDateTime.now());
        UserDTO dto = service.save(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/saveV2")
    public ResponseEntity<UserDTO> savev2(@RequestBody UserDTO userDTO) throws Exception {
        userDTO.setCreated(LocalDateTime.now());
        userDTO.setPass(SafetyConfiguration.encrypt(userDTO.getPass()));
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

    @PostMapping("/loginV2")
    public ResponseEntity<?> loginv2(@RequestBody UserDTO userDTO) throws Exception {
        NgaturLuwh test = new NgaturLuwh();
        String gaa = service.login(userDTO);
        String testis = SafetyConfiguration.decrypt(gaa);
        String asw = userDTO.getPass();
        if (testis.equals(asw)) {
            UserDTO gimm = service.findByEmail(userDTO.getEmail());
            String ancrit = LocalDateTime.now().plus(30, ChronoUnit.MINUTES).toString() + gimm.getUsername();
            test.setData(ancrit);
            test.setRoleId(gimm.getRoleId());
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/loginV3")
    public ResponseEntity<?> loginv3(@RequestBody UserDTO userDTO) throws Exception {
        // test.setData(service.login(userDTO));
        String val1 = SafetyConfiguration.decrypt(service.login(userDTO));
        String val2 = SafetyConfiguration.decrypt(userDTO.getPass());
        if (val1.equals(val2)) {
            NgaturLuwh test = new NgaturLuwh();
            UserDTO gimm = service.findByEmail(userDTO.getEmail());
            test.setData(LocalDateTime.now().plus(10, ChronoUnit.MINUTES).toString() + gimm.getUsername());
            test.setRoleId(gimm.getRoleId());
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
