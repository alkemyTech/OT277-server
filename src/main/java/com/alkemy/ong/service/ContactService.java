package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.entity.ContactEntity;

import java.util.List;

public interface ContactService {

    ContactDTO save(ContactDTO dto);

    List<ContactDTO> getContacts();

}