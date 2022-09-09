package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.mapper.impl.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;
    private final AmazonClient amazonClient;

    public SlideDTOResponse saveSlide(SlideDTO dto){
        SlideEntity entity = slideMapper.toEntity(dto);
        entity.setImageUrl(amazonClient.uploadFile(dto.getImage_b64(), UUID.randomUUID().toString()));
        Integer order = slideRepository.findNextMaxSlideOrder();
        entity.setSlideOrder(dto.getOrder()!=null && dto.getOrder()>order ? dto.getOrder():order+1);
        return slideMapper.toDtoResponse(slideRepository.save(entity));
    }
}