package com.example.up_final.image;

import lombok.Data;

@Data
public class ImageInfo {

    private String imageUrl;
    private String imageName;

    public ImageInfo(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }
}
