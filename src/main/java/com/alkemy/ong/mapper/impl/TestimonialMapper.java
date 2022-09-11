package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.entity.TestimonialEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper implements Mapper<TestimonialDTO, TestimonialEntity> {

    @Override
    public TestimonialDTO toDto(TestimonialEntity testimonialEntity) {
        TestimonialDTO dto = new TestimonialDTO();
        dto.setName(testimonialEntity.getName());
        dto.setImage(testimonialEntity.getImage());
        dto.setContent(testimonialEntity.getContent());
        return dto;
    }

    @Override
    public TestimonialEntity toEntity(TestimonialDTO testimonialDTO) {
        TestimonialEntity entity = new TestimonialEntity();
        entity.setName(testimonialDTO.getName());
        entity.setImage(testimonialDTO.getImage());
        entity.setContent(testimonialDTO.getContent());
        return entity;
    }

    @Override
    public TestimonialDTO toBasicDto(TestimonialEntity testimonialEntity) {
        return null;
    }
}
