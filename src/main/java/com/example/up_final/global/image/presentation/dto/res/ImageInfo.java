package com.example.up_final.global.image.presentation.dto.res;

public record ImageInfo(
        String imageUrl,
        String imageName
) {
    public static ImageInfo from(String imageUrl, String imageName) {
        return new ImageInfo(
                imageUrl,
                imageName
        );
    }
}
