package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseEMusicDto;
import com.eighteen.userservice.entity.*;
import com.eighteen.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EReccMusicService {

    private final EmotionRepository emotionRepository;

    private final MusicRepository musicRepository;

    private final EMusicRepository eMusicRepository;

    private final UserRepository userRepository;

    private final MyEighteenRepository myEighteenRepository;


    public ResponseEMusicDto getRecommendedMusicList(String userId, Integer emotionId) {

        Emotion emotion = emotionRepository.findById(emotionId).orElseThrow();
        List<EMusic> eMusics = eMusicRepository.findByEmotionAndPopularityGreaterThanEqualOrderByPopularityDesc(emotion, 30);
        User user = userRepository.findByUserId(userId);
        List<MusicDto> response = new ArrayList<>();
        Random random = new Random();
        if (eMusics.size() > 20) {
            for (int i = 0; i < 20; i++) {
                int randomIndex = random.nextInt(eMusics.size());
                EMusic randomElement = eMusics.get(randomIndex);
                MusicDto randomMusic = new ModelMapper().map(randomElement.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, randomElement.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);
            }
        } else {
            for (EMusic eMusic : eMusics) {
                MusicDto randomMusic = new ModelMapper().map(eMusic.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, eMusic.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);

            }
        }

        ResponseEMusicDto responseEMusicDto = new ResponseEMusicDto(response);

        return responseEMusicDto;
    }
}