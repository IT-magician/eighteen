package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseWMusicDto;
import com.eighteen.userservice.entity.WMusic;
import com.eighteen.userservice.entity.Weather;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WMusicService {

    private final WeatherRepository weatherRepository;

    private final MusicRepository musicRepository;

    private final WMusicRepository wMusicRepository;

    private final UserRepository userRepository;

    private final MyEighteenRepository myEighteenRepository;


    public ResponseWMusicDto getWMusicList(String userId, Integer weatherId) {

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

        ResponseWMusicDto responseWMusicDto = new ResponseWMusicDto(response);

        return responseWMusicDto;
    }
}