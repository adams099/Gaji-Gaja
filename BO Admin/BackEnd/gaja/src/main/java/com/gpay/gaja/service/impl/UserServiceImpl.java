package com.gpay.gaja.service.impl;

import com.gpay.gaja.common.dto.UserDTO;
import com.gpay.gaja.common.mapper.UserMapper;
import com.gpay.gaja.model.domain.User;
import com.gpay.gaja.model.repository.UserRepository;
import com.gpay.gaja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDTO findById(Long id) {
        Optional<User> UserOptional = repository.findById(id);
        return mapper.convertToDto(UserOptional.get());
    }

    @Override
    public UserDTO save(UserDTO UserDTO) {
        return mapper.convertToDto(repository.save(mapper.convertToEntity(UserDTO)));
    }

    @Override
    public List<UserDTO> getAll() {
        return mapper.convertToDto(repository.findAll());
    }

    @Override
    public String login(UserDTO userDTO) {
        User UserOptional = repository.findByEmail(userDTO.getEmail()).get();
        return UserOptional.getPass();
    }

    @Override
    public UserDTO findByEmail(String id) {
        Optional<User> UserOptional = repository.findByEmail(id);
        return mapper.convertToDto(UserOptional.get());
    }
}
