package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.entity.ContactEntity;
import com.alkemy.ong.mapper.impl.ContactMapper;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactDTO save(ContactDTO dto) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(dto)));
    }

    public List<ContactDTO> getContacts() {

        List<ContactEntity> entities = (List<ContactEntity>) contactRepository.findAll();
        List<ContactDTO> result = contactMapper.contactEntityList2DTOList(entities);
        return result;
    }
}
