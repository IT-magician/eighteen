package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.WMusic;
import com.eighteen.userservice.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WMusicRepository extends JpaRepository<WMusic, Integer> {

    public List<WMusic> findByWeatherAndPopularityGreaterThanEqualOrderByPopularityDesc(Weather weather, int popularity);
}