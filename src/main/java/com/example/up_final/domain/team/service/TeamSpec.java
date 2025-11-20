package com.example.up_final.domain.team.service;

import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.team.domain.Team;
import com.example.up_final.domain.team.presentation.dto.req.TeamCreateReq;
import com.example.up_final.domain.team.presentation.dto.req.TeamSearchReq;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpec {

    public static Specification<Team> search(TeamSearchReq req) {
        return Specification
                .where(categoryEq(req.category()))
                .and(keywordContains(req.keyword()));
    }

    private static Specification<Team> categoryEq(ContestCategory category) {
        return (root, query, cb) -> {
            if (category == null) return null;
            return cb.equal(root.get("category"), category);
        };
    }

    private static Specification<Team> keywordContains(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) return null;
            String like = "%" + keyword + "%";
            return cb.or(
                    cb.like(root.get("title"), like),
                    cb.like(root.get("content"), like),
                    cb.like(root.get("work"), like),
                    cb.like(root.get("requirements"), like),
                    cb.like(root.get("preferred"), like),
                    cb.like(root.get("contact"), like),
                    cb.like(root.get("description"), like),
                    cb.like(root.get("writer").get("name"), like)
            );
        };
    }
}
