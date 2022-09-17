package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.PageableResponse;


import java.util.List;

public interface CategoryService {

    PageableResponse getCategories(int page, int pageSize, String sortBy);


    CategoryDTO saveCategory(CategoryDTO dto);

    CategoryDTO getCategoryDTOById(String id);

    CategoryDTO update(CategoryDTO dto, String id);

    void delete(String id);

}
