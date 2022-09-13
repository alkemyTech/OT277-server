package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.ContactEntity;
import com.alkemy.ong.mapper.impl.ContactMapper;
import com.alkemy.ong.mapper.impl.UserMapper;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final EmailServiceImpl emailService;

    public ContactDTO save(ContactDTO dto) {
        ContactEntity entity = contactRepository.save(contactMapper.toEntity(dto));
        UserDto user =userMapper.toBasicDto(userService.getByEmail(entity.getEmail()));
        emailService.sendEmailTo(user);

        return contactMapper.toDto(entity);
    }

    public List<ContactDTO> getContacts() {

        List<ContactEntity> entities = (List<ContactEntity>) contactRepository.findAll();
        List<ContactDTO> result = contactMapper.contactEntityList2DTOList(entities);
        return result;
    }
}
