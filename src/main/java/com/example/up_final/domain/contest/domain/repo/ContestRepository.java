package com.example.up_final.domain.contest.domain.repo;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.contest.domain.enums.ContestCategory;
import com.example.up_final.domain.contest.domain.enums.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long>, JpaSpecificationExecutor<Contest> {

}
