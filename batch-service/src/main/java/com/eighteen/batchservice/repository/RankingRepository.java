package com.eighteen.batchservice.repository;

import com.eighteen.batchservice.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Integer> {
}
