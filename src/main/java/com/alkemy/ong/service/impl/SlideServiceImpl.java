package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;
    private final AmazonClient amazonClient;
    private final OrganizationServiceImpl organizationService;
    private final PageableUtils pageableUtils;

    public SlideDTOResponse saveSlide(SlideDTO dto) {
        SlideEntity entity = slideMapper.toEntity(dto);
        entity.setImageUrl(generateUrlAmazon(dto.getImage_b64()));
        Integer order = generateOrder(dto.getOrganization_id());
        entity.setSlideOrder(dto.getOrder() != null && dto.getOrder() > order ? dto.getOrder() : order + 1);
        return slideMapper.toDtoResponse(slideRepository.save(entity));
    }

    @Override
    public void deleteSlide(String id) {
        slideRepository.deleteById(getById(id).getId());
    }

    @Override
    public SlideDTOResponse update(String id, SlideDTO dto) {
        SlideEntity entity = getById(id);
        entity.setText(dto.getText() != null ? dto.getText() : entity.getText());
        entity.setImageUrl(dto.getImage_b64() == null ? entity.getImageUrl() : generateUrlAmazon(dto.getImage_b64()));
        entity.setOrganizationId(dto.getOrganization_id() == null ? entity.getOrganizationId() : organizationService.getById(dto.getOrganization_id()).getId());
        Integer orderDto = dto.getOrder();
        List<Integer> orderList = slideRepository.findSlideOrder(entity.getOrganizationId());
        if (orderList.contains(orderDto) && entity.getSlideOrder().equals(orderDto)) {
            entity.setSlideOrder(orderDto);
        } else if (!orderList.contains(orderDto)) {
            entity.setSlideOrder(orderDto);
        } else {
            entity.setSlideOrder(generateOrder(entity.getOrganizationId()) + 1);
        }
        return slideMapper.toDtoResponse(slideRepository.save(entity));
    }

    public SlideEntity getById(String id) {
        return slideRepository.findByIdAndSoftDeleteFalse(id).orElseThrow(
                () -> new ParamNotFound("Slide not found or disabled"));
    }

    @Override
    public PageableResponse getAll(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        var slide = slideRepository.findAll(pageable);
        var allSlides = slide.getContent();
        var SlideDTOResponse = allSlides.stream().map(slideMapper::toDtoResponse).collect(Collectors.toList());
        PageableResponse response = new PageableResponse();
        return pageableUtils.pageableUtils(slide, SlideDTOResponse, response, pageNumber, pageSize);
    }

    @Override
    public List<SlideEntity> slidesForOrg(String organization_id) {
        List <SlideEntity>  Slides = new ArrayList<>();
        List <SlideEntity> result = new ArrayList<>();
        Slides = slideRepository.findAll();
        for ( SlideEntity slide : Slides) {
            if(slide.getOrganizationId() == organization_id) {
                result.add(slide);
            }
        }
        return result;
    }

    public String generateUrlAmazon(String imageB64) {
        return amazonClient.uploadFile(imageB64, UUID.randomUUID().toString());
    }

    public Integer generateOrder(String organizationId) {
        Integer order = slideRepository.findNextMaxSlideOrder(organizationId);
        return order == null ? 0 : order;
    }

    public SlideDTOResponse getByIdResponse(String id) {
        return slideMapper.toDtoResponse(getById(id));
    }

}
