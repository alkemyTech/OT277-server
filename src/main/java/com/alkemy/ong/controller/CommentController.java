package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDtoResponse> saveComment(@Valid @RequestBody CommentDtoRequest comment){
        var saveComment = commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDtoResponse> updateComment(@Valid @RequestBody CommentDtoRequest comment,
                                                            @PathVariable String id){
        var updateComment = commentService.updateComment(comment, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id){
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
