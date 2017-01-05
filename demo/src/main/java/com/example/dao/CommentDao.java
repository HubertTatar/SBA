package com.example.dao;

import com.example.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

    List<Comment> findByPhotoId(Long photoId);
    Comment save(Comment comment);
    Comment find(Long commentId);
}
