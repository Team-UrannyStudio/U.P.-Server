package com.temp.up_v3.repository;

import com.temp.up_v3.domain.ImageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDetails, Long> {
    public ImageDetails findByContentId(String id);
}
