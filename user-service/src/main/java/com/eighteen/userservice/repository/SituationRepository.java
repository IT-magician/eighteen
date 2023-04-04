package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Integer> {
}
