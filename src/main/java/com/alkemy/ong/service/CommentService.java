package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {
    CommentDtoResponse saveComment(CommentDtoRequest comment);

    CommentDtoResponse updateComment(CommentDtoRequest comment, String commentId);

    void deleteComment(String id, HttpServletRequest request, Authentication auth);
}
