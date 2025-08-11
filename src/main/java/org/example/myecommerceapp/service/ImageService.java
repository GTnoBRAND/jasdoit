package org.example.myecommerceapp.service;

import org.example.myecommerceapp.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private ImageRepo repo;
    @Autowired
    public ImageService(ImageRepo repo) {
        this.repo = repo;
    }
}
