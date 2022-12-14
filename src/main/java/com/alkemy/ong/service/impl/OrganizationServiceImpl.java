package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.OrganizationMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    @Autowired
    private SlideService slideService;

    @Override
    public OrganizationDTO getPublicInformation(String id) {
        return organizationMapper.toBasicDto((getById(id)));
    }

    @Override
    public OrganizationDTO update(OrganizationDTO dto, String id) {
        String email = getById(id).getEmail();
        if (!dto.getEmail().equals(email)){
            validate(dto.getEmail());
        }
        OrganizationEntity entity = organizationMapper.toEntity(dto);
        entity.setEmail(email);
        entity.setId(id);
        return organizationMapper.toDto(organizationRepository.save(entity));
    }

    public OrganizationEntity getById(String id) {
        Optional <OrganizationEntity> result = organizationRepository.findById(id);
            if (result.isPresent()){
                OrganizationEntity entity = result.get();
                entity.setSlides(slideService.slidesForOrg(id));
                organizationRepository.save(entity);
                return entity;
            } else {
               throw  new ParamNotFound("Organization not found or disabled");
            }
    }

    @Override
    public OrganizationDTO save(OrganizationDTO dto) {
        validate(dto.getEmail());
        return organizationMapper.toDto(organizationRepository.save(organizationMapper.toEntity(dto)));
    }

    public void validate(String email) {
        if (organizationRepository.findByEmail(email).isPresent()) {
            throw new ParamNotFound("Organization mail already registered");
        }
    }

    @Override
    public void delete(String id) {
        organizationRepository.deleteById(getById(id).getId());
    }
}
