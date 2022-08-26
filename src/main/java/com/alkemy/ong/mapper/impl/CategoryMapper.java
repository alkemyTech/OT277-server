package com.alkemy.ong.mapper.impl;


import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<CategoryDto, CategoryEntity> {


    @Override
    public CategoryDto toDto(CategoryEntity categoryEntity) {
        var categoryDto = new CategoryDto();
        categoryDto.setName(categoryEntity.getName());
        categoryDto.setDescription(categoryEntity.getDescription());
        categoryDto.setImage(categoryEntity.getImage());
        categoryDto.setTimestamps(categoryEntity.getTimestamps());
        return categoryDto;
    }

    @Override
    public CategoryEntity toEntity(CategoryDto categoryDto) {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setDescription(categoryDto.getDescription());
        categoryEntity.setImage(categoryDto.getImage());
        categoryEntity.setTimestamps(categoryDto.getTimestamps());
        return categoryEntity;
    }

    @Override
    public CategoryDto toBasicDto(CategoryEntity categoryEntity) {
        return null;
    }
}
