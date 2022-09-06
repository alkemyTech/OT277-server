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
        Optional<NewEntity> entity = newRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("Does not exist a new with id ingresed");
        }
        newRepository.deleteById(id);
    }
    
    public NewDtoResponse getNewById(String id){
        Optional<NewEntity> entity = newRepository.findById(id);

        if(!entity.isPresent()) {
            try {
                throw new ParamNotFound("Error 404 not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        NewDtoResponse dto = newMapper.toNewDtoResponse(entity.get());
        return dto;
    }

    @Override
    public NewDtoResponse update(String id, NewDTO newDto) {
        Optional<NewEntity> newEntity = newRepository.findById(id);

        if(!newEntity.isPresent()) {
            try {
                throw new ParamNotFound("Does not exist a new with id ingresed");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        NewEntity newEntity2 = newEntity.get();
        newEntity2 = newMapper.toEntity(newDto);
        newEntity2.setId(id);

        NewDtoResponse newDtoResponse = newMapper.toNewDtoResponse(newRepository.save(newEntity2));

        return newDtoResponse;
    }
}


