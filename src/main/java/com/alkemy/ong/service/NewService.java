package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.NewDTO;

public interface NewService {
    NewDTO saveNews(NewDTO news);

    NewDTO getNewById(String id);

}
