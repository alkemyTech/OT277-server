package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageableUtils pageableUtils;

    public PageableResponse getCategories(int page, int pageSize, String sortBy) {
        Pageable p = PageRequest.of(page, pageSize, Sort.by(sortBy));

        var category = categoryRepository.findAll(p);
        var allCategories = category.getContent();
        var categoryDTO =allCategories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
        PageableResponse response = new PageableResponse();
        return pageableUtils.pageableUtils(category, categoryDTO, response, page, pageSize);
    }


    public CategoryDTO saveCategory(CategoryDTO dto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(dto);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryDTO result = categoryMapper.toDto(savedCategory);
        return result;
    }

    public CategoryDTO getCategoryDTOById(String id){
        Optional<CategoryEntity> entity = categoryRepository.findById(id);

        if(!entity.isPresent()) {
            try {
                throw new ParamNotFound("Error 404 not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        CategoryDTO categoryDTO = categoryMapper.toDto(entity.get());
        return categoryDTO;
    }

    public CategoryDTO update(CategoryDTO dto, String id) {

        if(!categoryRepository.findById(id).isPresent()){
            throw  new ParamNotFound("El id de category es invalido");
        }
        CategoryEntity entity = this.categoryMapper.toEntity(dto);
        entity.setId(id);
        return this.categoryMapper.toDto(this.categoryRepository.save(entity));
    }

    public void delete(String id) {
        Optional<CategoryEntity>entity = categoryRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de categoria no encontrada, no se pudo eliminar");
        }
        categoryRepository.deleteById(id);
    }
}
