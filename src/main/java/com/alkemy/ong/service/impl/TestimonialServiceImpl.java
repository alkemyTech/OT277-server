package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.entity.TestimonialEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.TestimonialMapper;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialMapper testimonialMapper;
    private final TestimonialRepository testimonialRepository;
    private final PageableUtils pageableUtils;
    private final AmazonClient amazonClient;

    @Override
    public TestimonialDTO save(TestimonialDTO dto) {
        TestimonialEntity entity = testimonialMapper.toEntity(dto);
        entity.setImage(generateUrlAmazon(dto.getImage()));
        return testimonialMapper.toDto(testimonialRepository.save(entity));
    }

    @Override
    public TestimonialDTO update(TestimonialDTO dto, String testId) {
        var testimonial = getById(testId);
        testimonial.setName(dto.getName());
        testimonial.setImage(dto.getImage());
        testimonial.setContent(dto.getContent());
        return testimonialMapper.toDto(testimonialRepository.save(testimonial));
    }

    @Override
    public TestimonialEntity getById(String testimonialId) {
        return testimonialRepository.findById(testimonialId).orElseThrow(
                () -> new ParamNotFound("Testimonial whit id : " + testimonialId + " not found"));
    }

    @Override
    public PageableResponse getAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        var testimonial = testimonialRepository.findAll(pageable);
        var allNews = testimonial.getContent();
        var testimonialDTO = allNews.stream().map(testimonialMapper::toDto).collect(Collectors.toList());
        PageableResponse response = new PageableResponse();
        return pageableUtils.pageableUtils(testimonial, testimonialDTO, response, pageNumber, pageSize);
    }

    public String delete(String id) {
        testimonialRepository.deleteById(getById(id).getId());
        return "Successfully deleted testimonial with id " + id;
    }

    public String generateUrlAmazon(String imageB64) {
        return amazonClient.uploadFile(imageB64, UUID.randomUUID().toString());
    }
}

