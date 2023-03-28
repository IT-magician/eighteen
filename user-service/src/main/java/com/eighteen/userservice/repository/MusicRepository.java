package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Integer> {

    public Music findByMusicId(Integer musicId);
}
