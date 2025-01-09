package com.temp.up_v3.repository;

import com.temp.up_v3.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUid(String uid);
    boolean existsByUid(String uid);
    Member findMemberByUid(String uid);
}
