package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.entity.ContactEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper implements Mapper<ContactDTO, ContactEntity> {

    @Override
    public ContactEntity toEntity(ContactDTO dto) {
        ContactEntity entity = new ContactEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    @Override
    public ContactDTO toBasicDto(ContactEntity entity) {
        ContactDTO response = new ContactDTO();
        response.setName(entity.getName());
        response.setPhone(entity.getPhone());
        response.setEmail(entity.getEmail());
        response.setMessage(entity.getMessage());
        return response;
    }

    @Override
    public ContactDTO toDto(ContactEntity entity) {

        return null;
    }
}

