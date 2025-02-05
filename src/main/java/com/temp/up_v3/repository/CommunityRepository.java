package com.temp.up_v3.repository;

import com.temp.up_v3.domain.Community;
import com.temp.up_v3.domain.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
}