package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.response.ResponseMusicDetailDto;
import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MusicFeature;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.MusicFeatureRepository;
import com.eighteen.userservice.repository.MusicRepository;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final UserRepository userRepository;

    private  final MusicRepository musicRepository;

    private final MusicFeatureRepository musicFeatureRepository;

    public ResponseMusicDetailDto getMusicDetail(Integer musicId, String userId) {

        User user = userRepository.findByUserId(userId);
        Music music = musicRepository.findByMusicId(musicId);
        MusicFeature musicFeature = musicFeatureRepository.findByMusicId(musicId);
        ResponseMusicDetailDto responseMusicDetailDto = new ResponseMusicDetailDto(user, music, musicFeature);
        return responseMusicDetailDto;
    }
}
