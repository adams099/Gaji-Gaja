package com.gpay.gaja.controller;

import com.gpay.gaja.common.dto.EmailAjaDTO;
import com.gpay.gaja.common.dto.NgaturLuwh;
import com.gpay.gaja.common.dto.ResetPassDTO;
import com.gpay.gaja.common.dto.SendEmail;
import com.gpay.gaja.common.dto.UserDTO;
import com.gpay.gaja.config.SafetyConfiguration;
import com.gpay.gaja.service.EmailService;
import com.gpay.gaja.service.ResetPassService;
import com.gpay.gaja.service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

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

    @Autowired
    EmailService services;

    @Autowired
    ResetPassService rse;

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

    @PostMapping("/findEmail")
    public ResponseEntity<UserDTO> findEmail(@RequestBody UserDTO userDTO) {
        try {
            UserDTO dto = service.findByEmail(userDTO.getEmail());
            return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
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
        if (userDTO.getId() == null || userDTO.getId() == 0) {
            userDTO.setCreated(LocalDateTime.now());
        } else {
            userDTO.setUpdate(LocalDateTime.now());
            if (userDTO.getStatId() == 4) {
                UserDTO dtos = service.save(userDTO);
                return new ResponseEntity<>(dtos, HttpStatus.CREATED);
            } else if (userDTO.getStatId() == 2) {
                userDTO.setUpdate(null);
            }
        }
        userDTO.setPass(SafetyConfiguration.encrypt(userDTO.getPass()));
        userDTO.setPin(SafetyConfiguration.encrypt(userDTO.getPin()));
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

    @GetMapping("/testing")
    public ResponseEntity<?> kirimemail(@RequestBody EmailAjaDTO e) {
        try {
            services.sendEmail(e.getEmail(), e.getSubject(), e.getBody());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception es) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/loginV2")
    public ResponseEntity<?> loginv2(@RequestBody UserDTO userDTO) throws Exception {
        NgaturLuwh test = new NgaturLuwh();
        String gaa = service.login(userDTO);
        String testis = SafetyConfiguration.decrypt(gaa);
        String asw = "";
        String asy = "";
        if (userDTO.getPass() == null && userDTO.getPin() != null) {
            asy = userDTO.getPin();
        } else {
            asw = userDTO.getPass();
        }
        if (userDTO.getPass() == null && userDTO.getPin() != null) {
            if (testis.equals(asy)) {
                UserDTO gimm = service.findByEmail(userDTO.getEmail());
                gimm.setLast(LocalDateTime.now());
                service.save(gimm);
                String ancrit = LocalDateTime.now().plus(30, ChronoUnit.MINUTES).toString() + gimm.getEmail();
                test.setData(ancrit);
                test.setRoleId(gimm.getRoleId());
                test.setStatId(gimm.getStatId());
                return new ResponseEntity<>(test, HttpStatus.OK);
            }
        } else {

            if (testis.equals(asw)) {
                UserDTO gimm = service.findByEmail(userDTO.getEmail());
                gimm.setLast(LocalDateTime.now());
                service.save(gimm);
                String ancrit = LocalDateTime.now().plus(30, ChronoUnit.MINUTES).toString() + gimm.getEmail();
                test.setData(ancrit);
                test.setRoleId(gimm.getRoleId());
                test.setStatId(gimm.getStatId());
                return new ResponseEntity<>(test, HttpStatus.OK);
            }
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
            test.setData(LocalDateTime.now().plus(10, ChronoUnit.MINUTES).toString() + gimm.getEmail());
            test.setRoleId(gimm.getRoleId());
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/otp")
    public ResponseEntity<?> createotp(@RequestBody ResetPassDTO rp) {
        Random random = new Random();
        int rand = random.nextInt(900000) + 100000;
        String randomString = String.valueOf(rand);
        rp.setOtp(randomString);
        rp.setDate(LocalDateTime.now());
        rse.save(rp);
        services.otp(rp.getEmail(), "OTP Ganti Password", randomString);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/semail")
    public ResponseEntity<?> sendEmail(@RequestBody SendEmail rp) {
        services.sendEmail(rp.getEmail(), rp.getSubject(), rp.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/akunsemail")
    public ResponseEntity<?> AkunSeMail(@RequestBody SendEmail rp) {
        services.register(rp.getEmail(), rp.getSubject(), rp.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cekotp")
    public ResponseEntity<?> checkotp(@RequestBody ResetPassDTO rp) {
        List<ResetPassDTO> test = rse.findByEmail(rp);
        LocalDateTime latestDate = null;
        int latestIndex = -1;
        String res = "";

        for (int i = 0; i < test.size(); i++) {
            ResetPassDTO dto = test.get(i);
            LocalDateTime currentDate = dto.getDate();

            if (latestDate == null || currentDate.isAfter(latestDate)) {
                latestDate = currentDate;
                latestIndex = i;
            }
        }

        if (latestDate.plusMinutes(5).isBefore(LocalDateTime.now())) {
            res = "Terlambat";
        } else {
            res = "Tidak Sama";
            ResetPassDTO kkk = test.get(latestIndex);
            if (rp.getOtp().equals(kkk.getOtp())) {
                res = "Sama";
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comId/{comId}")
    public ResponseEntity<List<UserDTO>> getByCompanyId(@PathVariable Long comId) {
        List<UserDTO> dtos = service.getCompanyId(comId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
