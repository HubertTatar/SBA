package com.example.controller;

import com.example.model.Comment;
import com.example.model.Photo;
import com.example.service.CommentService;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommentResource {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/add")
    public void addComment(@RequestBody Comment comment) {
        Photo photo = photoService.findByPhotoId(comment.getPhotoId());
        List<Comment> commentList = photo.getCommentList();
        comment.setPhoto(photo);
        commentService.save(comment);


    }

}
