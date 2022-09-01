package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.NewMapper;
import com.alkemy.ong.repository.NewRepository;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NewServiceImp implements NewService {

    private final NewRepository newRepository;
    private final NewMapper newMapper;
    @Override
    public NewDTO saveNews(NewDTO news) {
        NewEntity newEntity = newRepository.save(newMapper.toEntity(news));
        return newMapper.toDto(newRepository.save(newEntity));
    }

    @Override
    public void deleteNew(String id) {
        Optional<NewEntity> entity = newRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("No existe una NEW con el id ingresado");
        }
        newRepository.deleteById(id);

    }
}
