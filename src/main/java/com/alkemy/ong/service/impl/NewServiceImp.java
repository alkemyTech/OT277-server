package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.NewMapper;
import com.alkemy.ong.repository.CategoryRepository;
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

    private final CategoryRepository categoryRepository;
    @Override
    public NewDtoResponse saveNews(NewDTO news) {
        NewEntity newEntity = newRepository.save(newMapper.toEntity(news));
        return newMapper.toNewDtoResponse(newRepository.save(newEntity));
    }

    @Override
    public void deleteNew(String id) {
        NewEntity newEntity = getNew(id);
        newRepository.deleteById(id);
    }
    
    public NewDtoResponse getNewById(String id){
        NewEntity newEntity = getNew(id);
        NewDtoResponse dto = newMapper.toNewDtoResponse(newEntity);
        return dto;
    }

    @Override
    public NewDtoResponse update(String id, NewDTO newDto) {
        NewEntity newEntity = getNew(id);

        newEntity = newMapper.toEntity(newDto);
        newEntity.setId(id);
        return newMapper.toNewDtoResponse(newRepository.save(newEntity));
    }


    private NewEntity getNew(String newId){
        return newRepository.findById(newId).orElseThrow(
                ()->new ParamNotFound("New not found: "+ newId));
    }


}


