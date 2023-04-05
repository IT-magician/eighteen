package com.eighteen.userservice.repository;


import com.eighteen.userservice.entity.SMusic;
import com.eighteen.userservice.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SMusicRepository extends JpaRepository<SMusic, Integer> {

    public List<SMusic> findBySituationAndPopularityGreaterThanEqual(Situation situation, int popularity);
}