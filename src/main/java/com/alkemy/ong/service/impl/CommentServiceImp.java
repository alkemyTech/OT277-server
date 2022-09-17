package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.entity.CommentEntity;
import com.alkemy.ong.exception.InvalidUserException;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.CommentMapper;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.service.NewService;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.utils.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp  implements CommentService {

    private final UserService userService;
    private final NewService newService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserDetailsCustomService userDetailsCustomService;
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

    @Override
    public void deleteComment(String id, HttpServletRequest request, Authentication auth) {
        var tokenUsername = userDetailsCustomService.extractUsername(request);
        var comment = commentRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Comment whit id: "+id+" no found"));
        if(tokenUsername.equals(comment.getUserEntity().getUsername()) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ADMIN.getFullRoleName()))){
            commentRepository.delete(comment);
        } else if (!tokenUsername.equals(comment.getUserEntity().getUsername())) {
            throw new InvalidUserException("Invalid user, this user can not delete this comment");
        }
    }

    public List<CommentDtoResponse> getComments() {

        List<CommentEntity> entities = (List<CommentEntity>) commentRepository.findAll();
        List<CommentDtoResponse> result = commentMapper.commentEntityList2DTOList(entities);
        return result;
    }
}
