package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.CategoryEntity;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    public List<String> getCategories() {

        List<CategoryEntity> entities = (List<CategoryEntity>) categoryRepository.findAll();
        List<String> result = categoryMapper.categoryEntitySet2DTOSet(entities);
        return result;
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
