package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.entity.CategoryEntity;

import java.util.Set;

public interface CategoryService {

    Set<CategoryDTO> getCategories();

    CategoryDTO getCategoryDTOById(String id);



}
