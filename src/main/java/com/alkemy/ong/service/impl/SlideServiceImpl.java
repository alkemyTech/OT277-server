package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.security.dto.SlideDTOResponse;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    @Override
    public SlideDTOResponse getById(String id) {
        return slideMapper.toDtoResponse(slideRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Slide not found")));
    }
}
