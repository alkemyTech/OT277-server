package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.service.NewService;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImp  implements CommentService {

    private final UserService userService;
    private final NewService newService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    @Override
    public CommentDtoResponse saveComment(CommentDtoRequest comment) {
        var user = userService.getUserByID(comment.getUserId());
        var news = newService.getNewId(comment.getNewsId());
        var commentEntity = commentMapper.toEntity(comment);
        commentEntity.setNewEntity(news);
        commentEntity.setUserEntity(user);
        var response = commentRepository.save(commentEntity);
        return commentMapper.toDto(response);
    }

    @Override
    public CommentDtoResponse updateComment(CommentDtoRequest comment, String commentId) {
        var commentEntity = commentRepository.findById(commentId).orElseThrow(
                ()-> new ParamNotFound("Comment whit id: "+commentId+" not found")
        );
        commentEntity.setBody(comment.getBody());
        return commentMapper.toDto(commentRepository.save(commentEntity));
    }
}
