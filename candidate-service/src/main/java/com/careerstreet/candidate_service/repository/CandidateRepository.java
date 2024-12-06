package com.careerstreet.candidate_service.repository;


import com.careerstreet.candidate_service.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository <Candidate, Long> {
    Candidate findCandidateByUsername(String username);

//    @Query("SELECT c.email FROM Candidate c WHERE c.id = :candidateId")
//    String findEmailById(@Param("candidateId") Long candidateId);
}
