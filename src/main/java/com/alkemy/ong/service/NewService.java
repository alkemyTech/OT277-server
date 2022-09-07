package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;

public interface NewService {
    
    NewDtoResponse saveNews(NewDTO news);

    void deleteNew(String id);

    NewDtoResponse getNewById(String id);

    NewDtoResponse update(String id, NewDTO newDto);
}
