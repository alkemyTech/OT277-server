package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImp  implements CommentService {

    private final UserRepository userRepository;
    private final NewRepository newRepository;
    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;
    @Override
    public CommentDtoResponse saveComment(CommentDtoRequest comment) {
        var user = userRepository.findById(comment.getUser()).orElseThrow(
                ()-> new ParamNotFound("User whit id: "+ comment.getUser()+" not found.")
        );
        var news = newRepository.findById(comment.getNews()).orElseThrow(
                ()-> new ParamNotFound("News whit id: "+ comment.getNews()+" not found.")
        );
        var commentEntity = commentMapper.toEntity(comment);
        commentEntity.setNewEntity(news);
        commentEntity.setUserEntity(user);
        var response = commentRepository.save(commentEntity);
        return commentMapper.toDto(response);
    }
}
