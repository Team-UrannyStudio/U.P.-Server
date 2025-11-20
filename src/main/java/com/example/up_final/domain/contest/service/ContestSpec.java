package com.example.up_final.domain.contest.service;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Region;
import com.example.up_final.domain.contest.domain.enums.Target;
import com.example.up_final.domain.contest.presentation.dto.req.ContestSearchReq;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ContestSpec {

    public static Specification<Contest> search(ContestSearchReq req) {
        return Specification
                .where(categoryEq(req.category()))
                .and(targetEq(req.target()))
                .and(regionEq(req.region()))
                .and(timeFilter(req.timeFilter()))
                .and(keywordContains(req.keyword()));
    }

    private static Specification<Contest> categoryEq(ContestCategory category) {
        return (root, query, cb) -> {
            if (category == null) return null;
            return cb.equal(root.get("category"), category);
        };
    }

    private static Specification<Contest> targetEq(Target target) {
        return (root, query, cb) -> {
            if (target == null) return null;
            return cb.equal(root.get("target"), target);
        };
    }

    private static Specification<Contest> regionEq(Region region) {
        return (root, query, cb) -> {
            if (region == null) return null;
            return cb.equal(root.get("region"), region);
        };
    }

    private static Specification<Contest> timeFilter(String filter) {
        return (root, query, cb) -> {
            if (filter == null || filter.equalsIgnoreCase("ALL")) return null;

            LocalDate today = LocalDate.now();

            return switch (filter.toUpperCase()) {
                case "ONGOING" ->
                        cb.and(
                                cb.lessThanOrEqualTo(root.get("registration_start"), today),
                                cb.greaterThanOrEqualTo(root.get("registration_end"), today)
                        );

                case "UPCOMING" ->
                        cb.greaterThan(root.get("registration_start"), today);

                case "ENDED" ->
                        cb.lessThan(root.get("registration_end"), today);

                default -> null;
            };
        };
    }

    private static Specification<Contest> keywordContains(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return null;

            String like = "%" + keyword + "%";
            return cb.or(
                    cb.like(root.get("title"), like),
                    cb.like(root.get("host"), like),
                    cb.like(root.get("registration_way"), like),
                    cb.like(root.get("registration_link"), like),
                    cb.like(root.get("award"), like),
                    cb.like(root.get("home_page"), like),
                    cb.like(root.get("contact"), like),
                    cb.like(root.get("etc"), like),
                    cb.like(root.get("writer").get("name"), like)
            );
        };
    }
}