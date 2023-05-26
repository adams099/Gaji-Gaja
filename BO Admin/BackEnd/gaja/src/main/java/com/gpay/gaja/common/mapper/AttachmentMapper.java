package com.gpay.gaja.common.mapper;

import com.gpay.gaja.common.dto.AttachmentDTO;
import com.gpay.gaja.model.domain.Attachment;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttachmentMapper implements EntityToDTOMapper<Attachment, AttachmentDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Attachment createEntity(AttachmentDTO imageDTO) {
        return mapper.map(imageDTO, Attachment.class);
    }

    @Override
    public Attachment updateEntity(AttachmentDTO imageDTO) {
        return null;
    }

    @Override
    public AttachmentDTO convertToDto(Attachment image) {
        return mapper.map(image, AttachmentDTO.class);
    }

    @Override
    public List<AttachmentDTO> convertToDto(Iterable<Attachment> images) {
        List<AttachmentDTO> dtos = new ArrayList<AttachmentDTO>();
        for (Attachment entity : images) {
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    @Override
    public Attachment convertToEntity(AttachmentDTO imageDTO) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper.map(imageDTO, Attachment.class);
    }

    @Override
    public List<Attachment> convertToEntity(Iterable<AttachmentDTO> imageDTOS) {
        return null;
    }
}
