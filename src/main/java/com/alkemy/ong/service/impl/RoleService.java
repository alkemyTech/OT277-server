package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.RoleEntity;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.utils.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getUserRole(){
        return roleRepository.findByName(RoleType.USER.getFullRoleName());
    }

    //TODO: hacer interface

}
