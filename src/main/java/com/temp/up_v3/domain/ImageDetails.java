package com.temp.up_v3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "image_details")
public class ImageDetails {

    @Id
    @Column(nullable = false)
    private String contentId;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public ImageDetails(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }
}