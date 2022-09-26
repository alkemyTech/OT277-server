package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;

import java.util.List;

public interface SlideService {

    SlideDTOResponse saveSlide(SlideDTO dto);

    PageableResponse getAll(int pageNumber, int pageSize, String sortBy);

    List<SlideEntity> slidesForOrg(String organizaion_id);

    SlideDTOResponse getByIdResponse(String id);

    SlideEntity getById(String id);

    void deleteSlide(String id);

    SlideDTOResponse update(String id, SlideDTO dto);

    String generateUrlAmazon(String imageB64);

    Integer generateOrder(String organizationId);
}
