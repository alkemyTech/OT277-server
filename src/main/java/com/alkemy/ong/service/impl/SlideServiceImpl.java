package com.alkemy.ong.service.impl;

import com.alkemy.ong.security.dto.SlideDTOResponse;
import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.mapper.impl.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;
    private final AmazonClient amazonClient;

    @Override
    public List<SlideDTOResponse> getSlides() {
        return slideMapper.toDtoResponseList(slideRepository.findAll());

    public SlideDTOResponse saveSlide(SlideDTO dto) {
        SlideEntity entity = slideMapper.toEntity(dto);
        entity.setImageUrl(amazonClient.uploadFile(dto.getImage_b64(), UUID.randomUUID().toString()));
        Integer order = slideRepository.findNextMaxSlideOrder();
        order = order == null ? 0 : order;
        entity.setSlideOrder(dto.getOrder() != null && dto.getOrder() > order ? dto.getOrder() : order + 1);
        return slideMapper.toDtoResponse(slideRepository.save(entity));

    }
}
