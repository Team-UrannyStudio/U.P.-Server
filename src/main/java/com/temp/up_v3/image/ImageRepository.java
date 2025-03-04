package com.temp.up_v3.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDetails, Long> {

    public ImageDetails findByContentId(String contentId);
}
