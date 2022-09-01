package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.OrganizationDTO;


import java.util.List;

public interface CategoryService {

    List<String> getCategories();


    CategoryDTO saveCategory(CategoryDTO dto);

    CategoryDTO getCategoryDTOById(String id);

    CategoryDTO update(CategoryDTO dto, String id);


}
