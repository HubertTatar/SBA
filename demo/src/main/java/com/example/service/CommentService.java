package com.example.service;

import com.example.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findByPhotoId(Long photoId);
    Comment findOne(Long commentId);
}
