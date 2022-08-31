package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements Mapper<CategoryDTO, CategoryEntity> {

    @Override
    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());

        return entity;
    }

    @Override
    public CategoryDTO toBasicDto(CategoryEntity entity) {
        CategoryDTO response = new CategoryDTO();

        response.setName(entity.getName());

        return response;
    }

    @Override
    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO response = new CategoryDTO();

        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setImage(entity.getImage());
        response.setTimestamps(entity.getTimestamps());

        return response;
    }


    public List<String> categoryEntitySet2DTOSet(List<CategoryEntity> CategoryEntitiesList){

        List<String> CategoriesName = new ArrayList<>();

        for (CategoryEntity entity: CategoryEntitiesList){
            CategoriesName.add(entity.getName());
        }
        return CategoriesName;
    }
}
