package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseSMusicDto;
import com.eighteen.userservice.entity.SMusic;
import com.eighteen.userservice.entity.Situation;
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
public class SMusicService {

    private final SituationRepository situationRepository;

    private final MusicRepository musicRepository;

    private final SMusicRepository sMusicRepository;

    private final UserRepository userRepository;

    private final MyEighteenRepository myEighteenRepository;


    public ResponseSMusicDto getSMusicList(String userId, Integer situationId) {

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

        ResponseSMusicDto responseSMusicDto = new ResponseSMusicDto(response);

        return responseSMusicDto;
    }
}