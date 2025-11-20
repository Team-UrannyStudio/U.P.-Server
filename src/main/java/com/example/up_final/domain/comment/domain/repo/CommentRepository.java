package com.example.up_final.domain.comment.domain.repo;

import com.example.up_final.domain.comment.domain.Comment;
import com.example.up_final.domain.comment.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentIdAndParentType(Long parent_id, ContentType parent_type);
}