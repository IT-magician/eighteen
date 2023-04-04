package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseRecommendDto;
import com.eighteen.userservice.entity.*;
import com.eighteen.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final EmotionRepository emotionRepository;

    private final SituationRepository situationRepository;

    private final WeatherRepository weatherRepository;

    private final EMusicRepository eMusicRepository;

    private final SMusicRepository sMusicRepository;

    private final WMusicRepository wMusicRepository;

    private final UserRepository userRepository;

    private final MyEighteenRepository myEighteenRepository;

    private final RestTemplate restTemplate;

    private final Environment env;
    private final MusicRepository musicRepository;

    public ResponseRecommendDto getEMusicList(String userId, Integer emotionId) {

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

        ResponseRecommendDto responseRecommendDto = new ResponseRecommendDto(response);

        return responseRecommendDto;
    }

    public ResponseRecommendDto getSMusicList(String userId, Integer situationId) {

        Situation situation = situationRepository.findById(situationId).orElseThrow();
        List<SMusic> sMusics = sMusicRepository.findBySituationAndPopularityGreaterThanEqualOrderByPopularityDesc(situation, 30);
        User user = userRepository.findByUserId(userId);
        List<MusicDto> response = new ArrayList<>();
        Random random = new Random();
        if (sMusics.size() > 20) {
            for (int i = 0; i < 20; i++) {
                int randomIndex = random.nextInt(sMusics.size());
                SMusic randomElement = sMusics.get(randomIndex);
                MusicDto randomMusic = new ModelMapper().map(randomElement.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, randomElement.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);
            }
        } else {
            for (SMusic sMusic : sMusics) {
                MusicDto randomMusic = new ModelMapper().map(sMusic.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, sMusic.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);

            }
        }

        ResponseRecommendDto responseRecommendDto = new ResponseRecommendDto(response);

        return responseRecommendDto;
    }

    public ResponseRecommendDto getWMusicList(String userId, Integer weatherId) {

        Weather weather = weatherRepository.findById(weatherId).orElseThrow();
        List<WMusic> wMusics = wMusicRepository.findByWeatherAndPopularityGreaterThanEqualOrderByPopularityDesc(weather, 30);
        User user = userRepository.findByUserId(userId);
        List<MusicDto> response = new ArrayList<>();
        Random random = new Random();
        if (wMusics.size() > 20) {
            for (int i = 0; i < 20; i++) {
                int randomIndex = random.nextInt(wMusics.size());
                WMusic randomElement = wMusics.get(randomIndex);
                MusicDto randomMusic = new ModelMapper().map(randomElement.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, randomElement.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);
            }
        } else {
            for (WMusic wMusic : wMusics) {
                MusicDto randomMusic = new ModelMapper().map(wMusic.getMusic(), MusicDto.class);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, wMusic.getMusic());
                if (myEighteen == null) {
                    randomMusic.setIsEighteen(Boolean.FALSE);
                }
                randomMusic.setIsEighteen(Boolean.TRUE);
                response.add(randomMusic);

            }
        }

        ResponseRecommendDto responseRecommendDto = new ResponseRecommendDto(response);

        return responseRecommendDto;
    }

    public ResponseRecommendDto getEighteenRecommend(String userId) {

        String recommendUrl = String.format(env.getProperty("flask.url")) + "/recommend/" + userId;
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<List> response = restTemplate.exchange(recommendUrl, HttpMethod.GET, entity, List.class);
        List<Integer> musiclist = response.getBody();
        User user = userRepository.findByUserId(userId);
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Integer musicId : musiclist) {
            Music music = musicRepository.findByMusicId(musicId);
            MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, music);
            MusicDto musicDto = new ModelMapper().map(music, MusicDto.class);
            if (myEighteen == null) {
                musicDto.setIsEighteen(Boolean.FALSE);
            }
            else {
                musicDto.setIsEighteen(Boolean.TRUE);
            }
            musicDtos.add(musicDto);
        }

        ResponseRecommendDto responseRecommendDto = new ResponseRecommendDto(musicDtos);
        return responseRecommendDto;
    }
}
