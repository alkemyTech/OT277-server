package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.Mapper;
import com.alkemy.ong.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewMapper implements Mapper<NewDTO, NewEntity> {

    @Lazy
    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;

    //No se usa
    @Override
    public NewDTO toDto(NewEntity newEntity) {
        var newDto = new NewDTO();
        newDto.setName(newEntity.getName());
        newDto.setContent(newEntity.getContent());
        newDto.setImage(newEntity.getImage());
        newDto.setTimestamps(newEntity.getTimestamps());
        newDto.setType(newEntity.getType());
        newDto.setCategoryDto(categoryMapper.toDto(newEntity.getCategoryEntity()));
        return newDto;
    }

    //Precondicion recibir un id de new y un id de category correcto
    @Override
    public NewEntity toEntity(NewDTO newDto) {
        NewEntity newEntity = new NewEntity();
        newEntity.setName(newDto.getName());
        newEntity.setContent(newDto.getContent());
        newEntity.setImage(newDto.getImage());
        newEntity.setTimestamps(newDto.getTimestamps());
        newEntity.setType(newDto.getType());
        Optional<CategoryEntity> categoryEntity =  categoryRepository.findById(newDto.getCategoryId());

        if(!categoryEntity.isPresent()) {
            try {
                throw new ParamNotFound("Does not exist a category with id ingresed");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        newEntity.setCategoryEntity((List<CategoryEntity>) categoryEntity.get());
        return newEntity;
    }

    @Override
    public NewDTO toBasicDto(NewEntity newEntity) {
        return null;
    }

    public NewDtoResponse toNewDtoResponse(NewEntity entity){
        NewDtoResponse dto = new NewDtoResponse();
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setTimestamps(entity.getTimestamps());
        dto.setType(entity.getType());
        dto.setCategory(categoryMapper.toDto(entity.getCategoryEntity()));
        return dto;
    }


}