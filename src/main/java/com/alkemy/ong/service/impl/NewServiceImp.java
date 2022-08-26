package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.mapper.impl.NewMapper;
import com.alkemy.ong.repository.NewRepository;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class NewServiceImp implements NewService {

    private final NewRepository newRepository;
    private final NewMapper newMapper;
    @Override
    public NewDto saveNews(NewDto news) {
        NewEntity newEntity = newRepository.save(newMapper.toEntity(news));
        return newMapper.toDto(newRepository.save(newEntity));
    }
}
