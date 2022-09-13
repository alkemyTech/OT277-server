package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.entity.TestimonialEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.TestimonialMapper;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialMapper testimonialMapper;

    private final TestimonialRepository testimonialRepository;

    @Override
    public TestimonialDTO save(TestimonialDTO dto) {
        TestimonialEntity entity = testimonialMapper.toEntity(dto);
        return testimonialMapper.toDto(testimonialRepository.save(entity));
    }

    @Override
    public TestimonialDTO update(TestimonialDTO dto, String testId) {
        var testimonial = getTestimonialEntity(testId);
        testimonial.setName(dto.getName());
        testimonial.setImage(dto.getImage());
        testimonial.setContent(dto.getContent());
        return testimonialMapper.toDto(testimonialRepository.save(testimonial));
    }

    public TestimonialEntity getTestimonialEntity(String testId){
        return testimonialRepository.findById(testId).orElseThrow(
                ()-> new ParamNotFound("Testimonial whit id : "+testId+" not found"));
    }
}

