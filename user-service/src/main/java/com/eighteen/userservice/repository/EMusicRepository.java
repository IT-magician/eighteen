package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.EMusic;
import com.eighteen.userservice.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EMusicRepository extends JpaRepository<EMusic, Integer> {

    public List<EMusic> findByEmotionAndPopularityGreaterThanEqualOrderByPopularityDesc(Emotion emotion, int popularity);
}