package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.UserDTO;
import com.gpay.gaja.model.domain.User;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements EntityToDTOMapper<User, UserDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public User createEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    @Override
    public User updateEntity(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO convertToDto(User user) {
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> convertToDto(Iterable<User> users) {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        for (User entity : users) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(userDTO, User.class);
    }

    @Override
    public List<User> convertToEntity(Iterable<UserDTO> userDTOS) {
        return null;
    }
}
