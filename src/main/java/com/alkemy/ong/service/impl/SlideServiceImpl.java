package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.exception.ParamNotFound;
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
    public void deleteSlide(String id) {
        slideRepository.deleteById(getById(id).getId());
    }

    @Override
    public SlideDTOResponse update(String id, SlideDTO dto) {
        SlideEntity entity = getById(id);
        entity.setText(dto.getText() != null ? dto.getText() : entity.getText());
        entity.setImageUrl(dto.getImage_b64() == null ? entity.getImageUrl() : generateUrlAmazon(dto.getImage_b64()));
        entity.setSlideOrder(dto.getOrder() == null ? entity.getSlideOrder() : (!slideRepository.existsBySlideOrder(dto.getOrder())) ? dto.getOrder() : generateOrder());
        return slideMapper.toDtoResponse(slideRepository.save(entity));
    }

    public SlideEntity getById(String id) {
        return slideRepository.findByIdAndSoftDeleteFalse(id).orElseThrow(
                () -> new ParamNotFound("Slide not found or disabled"));
    }

    @Override
    public List<SlideDTOResponse> getSlides() {
        return slideMapper.toDtoResponseList(slideRepository.findAll());
    }

    public SlideDTOResponse saveSlide(SlideDTO dto) {
        SlideEntity entity = slideMapper.toEntity(dto);
        entity.setImageUrl(generateUrlAmazon(dto.getImage_b64()));
        Integer order = generateOrder();
        entity.setSlideOrder(dto.getOrder() != null && dto.getOrder() > order ? dto.getOrder() : order + 1);
        return slideMapper.toDtoResponse(slideRepository.save(entity));
    }

    public String generateUrlAmazon(String imageB64) {
        return amazonClient.uploadFile(imageB64, UUID.randomUUID().toString());
    }

    public Integer generateOrder() {
        Integer order = slideRepository.findNextMaxSlideOrder();
        return order == null ? 0 : order;
    }

    public SlideDTOResponse getByIdResponse(String id) {
        return slideMapper.toDtoResponse(getById(id));
    }

}
