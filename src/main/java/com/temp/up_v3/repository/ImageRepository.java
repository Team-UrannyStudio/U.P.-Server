package com.temp.up_v3.repository;

import com.temp.up_v3.domain.ContestImageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ContestImageDetails, Long> {
}
