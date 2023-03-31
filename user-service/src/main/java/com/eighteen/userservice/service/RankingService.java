package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.response.ResponseProfileDto;
import com.eighteen.userservice.dto.response.ResponseRankingDto;
import com.eighteen.userservice.entity.AgeGender;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.Ranking;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.AgeGenderRepository;
import com.eighteen.userservice.repository.MyEighteenRepository;
import com.eighteen.userservice.repository.RankingRepository;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final AgeGenderRepository ageGenderRepository;

    private final RankingRepository rankingRepository;

    private final MyEighteenRepository myEighteenRepository;

    private final UserRepository userRepository;

    public ResponseRankingDto getRanking(String userId, String gender, Integer age) {

        User user = userRepository.findByUserId(userId);
        String agId = gender + age;
        AgeGender ageGender = ageGenderRepository.findByAgId(agId);
        List<Ranking> rankings = rankingRepository.findByAgeGender(ageGender);
        List<MusicDto> musicDtos = new ArrayList<>();
        for (Ranking ranking : rankings) {
            MusicDto musicDto = new ModelMapper().map(ranking.getMusic(), MusicDto.class);
            MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, ranking.getMusic());
            if (myEighteen == null) {
                musicDto.setIsEighteen(Boolean.FALSE);
            }
            else {
                musicDto.setIsEighteen(Boolean.TRUE);
            }
            musicDtos.add(musicDto);
        }
        ResponseRankingDto responseRankingDto = new ResponseRankingDto(musicDtos);
        return responseRankingDto;
    }
}
