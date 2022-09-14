package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;

public interface CommentService {
    CommentDtoResponse saveComment(CommentDtoRequest comment);

    CommentDtoResponse updateComment(CommentDtoRequest comment, String commentId);

    void deleteComment(String id);
}
