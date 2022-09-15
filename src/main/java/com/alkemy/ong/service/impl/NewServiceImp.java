package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.NewMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.NewRepository;
import com.alkemy.ong.service.NewService;
import com.alkemy.ong.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NewServiceImp implements NewService {

    private final NewRepository newRepository;
    private final NewMapper newMapper;

    private final PageableUtils pageableUtils;
    @Override
    public NewDtoResponse saveNews(NewDTO news) {
        NewEntity newEntity = newRepository.save(newMapper.toEntity(news));
        return newMapper.toNewDtoResponse(newRepository.save(newEntity));
    }

    @Override
    public void deleteNew(String id) {
        NewEntity newEntity = getNewId(id);
        newRepository.deleteById(id);
    }
    
    public NewDtoResponse getNewById(String id){
        NewEntity newEntity = getNewId(id);
        NewDtoResponse dto = newMapper.toNewDtoResponse(newEntity);
        return dto;
    }

    @Override
    public NewDtoResponse update(String id, NewDTO newDto) {
        NewEntity newEntity = getNewId(id);

        newEntity = newMapper.toEntity(newDto);
        newEntity.setId(id);
        return newMapper.toNewDtoResponse(newRepository.save(newEntity));
    }


    public NewEntity getNewId(String newId){
        return newRepository.findById(newId).orElseThrow(
                ()->new ParamNotFound("New not found: "+ newId));
    }

    @Override
    public PageableResponse getAllPosts(int pageNumber, int pageSize, String sortBy) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        var news = newRepository.findAll(p);
        var allNews = news.getContent();
        var newDTO =allNews.stream().map(newMapper::toNewDtoResponse).collect(Collectors.toList());
        PageableResponse response = new PageableResponse();
        return pageableUtils.pageableUtils(news, newDTO, response, pageNumber, pageSize);
    }


}


