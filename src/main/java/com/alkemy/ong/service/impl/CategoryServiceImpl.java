package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.mapper.impl.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    public Set<CategoryDTO> getCategories() {

        Set<CategoryEntity> entities = (Set<CategoryEntity>) categoryRepository.findAll();
        Set<CategoryDTO> result = categoryMapper.categoryEntitySet2DTOSet(entities);
        return result;
    }

    public CategoryDTO saveCategory(CategoryDTO dto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(dto);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryDTO result = categoryMapper.toDto(savedCategory);
        return result;
    }
}
