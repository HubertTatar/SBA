package com.example.service;

import com.example.model.Photo;
import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhotoService {
    List<Photo> findAll();
    List<Photo> findByUser(User user);
    Photo findByPhotoId(Long photoId);
    Photo save(Photo photo);
}
