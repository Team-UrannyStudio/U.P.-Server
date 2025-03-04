package com.temp.up_v3.post;

import com.temp.up_v3.post.dto.PostListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);
    List<Post> findByCategory(String category);
    List<Post> findByParticipants(String participants);
    List<Post> findByLocation(String location);
}
