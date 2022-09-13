package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.mapper.impl.ContactMapper;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactDTO save(ContactDTO dto) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(dto)));
    }
}
