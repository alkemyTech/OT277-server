package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;


import java.util.List;

public interface CategoryService {

    List<String> getCategories();


    CategoryDTO saveCategory(CategoryDTO dto);

    CategoryDTO getCategoryDTOById(String id);

}
