package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MusicFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicFeatureRepository extends JpaRepository<MusicFeature, Integer> {

    public MusicFeature findByMusicId(Integer musicId);
}
