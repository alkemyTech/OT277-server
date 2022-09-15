package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.entity.NewEntity;

public interface NewService {
    
    NewDtoResponse saveNews(NewDTO news);

    void deleteNew(String id);

    NewDtoResponse getNewById(String id);

    NewDtoResponse update(String id, NewDTO newDto);

    NewEntity getNewId(String newId);

    PageableResponse getAllPosts(int pageNumber, int pageSize, String sortBy);
}
