package com.example.controller;

import com.example.model.Photo;
import com.example.model.User;
import com.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PhotoResource {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo/upload")
    public String upload(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        MultipartHttpServletRequest mpReq = (MultipartHttpServletRequest) servletRequest;
        Iterator<String> it = mpReq.getFileNames();
        MultipartFile mpFile = mpReq.getFile(it.next());
        String fileName = mpFile.getOriginalFilename();

        String path = new File("src\\main\\resources\\static\\images").getAbsolutePath() + "\\" + fileName;
        try{
            mpFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "upload failed";
        }
        return "upload success";
    }

    @PostMapping("/photo/add")
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.save(photo);
    }

    @PostMapping("/photo/user")
    public List<Photo> getPhotosByUser(@RequestBody User user) {
        return photoService.findByUser(user);
    }

    @PostMapping("/photo/photoId")
    public Photo getPhotosById(@RequestBody Long photoId) {
        return photoService.findByPhotoId(photoId);
    }

    @PostMapping("/photo/update")
    public void updatePhoto(@RequestBody Photo photo) {
        Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
        currentPhoto.setLikes(photo.getLikes());
        photoService.save(currentPhoto);
    }
}
