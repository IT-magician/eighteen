package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseRankingDto;
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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final UserRepository userRepository;

    private final MyEighteenRepository myEighteenRepository;

    private final RestTemplate restTemplate;

    private final Environment env;

    private final MusicRepository musicRepository;

    private final RankingService rankingService;

    public ResponseRecommendDto getEMusicList(String userId, Integer emotionId) {

        User user = userRepository.findByUserId(userId);
        String emotionUrl = String.format(env.getProperty("flask.url")) + "/recommend/emotion/" + emotionId;
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<List> response = restTemplate.exchange(emotionUrl, HttpMethod.GET, entity, List.class);
        List<Integer> musicList = response.getBody();
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Integer musicId : musicList) {
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

    public ResponseRecommendDto getSMusicList(String userId, Integer situationId) {

        User user = userRepository.findByUserId(userId);
        String emotionUrl = String.format(env.getProperty("flask.url")) + "/recommend/situation/" + situationId;
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<List> response = restTemplate.exchange(emotionUrl, HttpMethod.GET, entity, List.class);
        List<Integer> musicList = response.getBody();
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Integer musicId : musicList) {
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

    public ResponseRecommendDto getWMusicList(String userId, Integer weatherId) {

        User user = userRepository.findByUserId(userId);
        String emotionUrl = String.format(env.getProperty("flask.url")) + "/recommend/weather/" + weatherId;
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<List> response = restTemplate.exchange(emotionUrl, HttpMethod.GET, entity, List.class);
        List<Integer> musicList = response.getBody();
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Integer musicId : musicList) {
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

    public ResponseRecommendDto getEighteenRecommend(String userId) {

        User user = userRepository.findByUserId(userId);
        List<MyEighteen> myEighteens = myEighteenRepository.findByUser(user);
        if (myEighteens.size() > 0) {
            String recommendUrl = String.format(env.getProperty("flask.url")) + "/recommend/" + userId;
            HttpEntity<String> entity = new HttpEntity<>(null);
            ResponseEntity<List> response = restTemplate.exchange(recommendUrl, HttpMethod.GET, entity, List.class);
            List<Integer> musicList = response.getBody();
            List<MusicDto> musicDtos = new ArrayList<>();
            for (Integer musicId : musicList) {
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
        ResponseRecommendDto responseRecommendDto = new ResponseRecommendDto();
        return responseRecommendDto;
    }
}
