package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.entity.TestimonialEntity;

public interface TestimonialService {
    public TestimonialDTO save(TestimonialDTO dto);

    TestimonialDTO update(TestimonialDTO dto, String testId);

    TestimonialEntity getById(String testimonialId);

    PageableResponse getAll(int pageNumber, int pageSize, String sortBy);

    String delete(String id);

    String generateUrlAmazon(String imageB64);
}
