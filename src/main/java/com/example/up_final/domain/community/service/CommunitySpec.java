package com.example.up_final.domain.community.service;

import com.example.up_final.domain.community.domain.Community;
import com.example.up_final.domain.community.domain.enums.CommunityCategory;
import com.example.up_final.domain.community.presentation.dto.req.CommunitySearchReq;
import org.springframework.data.jpa.domain.Specification;

public class CommunitySpec {

    public static Specification<Community> search(CommunitySearchReq req) {
        return Specification
                .where(categoryEq(req.category()))
                .and(keywordContains(req.keyword()));
    }

    private static Specification<Community> categoryEq(CommunityCategory category) {
        return (root, query, cb) -> {
            if (category == null) return null;
            return cb.equal(root.get("category"), category);
        };
    }

    private static Specification<Community> keywordContains(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) return null;
            String like = "%" + keyword + "%";
            return cb.or(
                    cb.like(root.get("title"), like),
                    cb.like(root.get("content"), like),
                    cb.like(root.get("writer").get("name"), like)
            );
        };
    }
}