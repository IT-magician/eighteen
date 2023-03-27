package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.request.RequestEighteenDto;
import com.eighteen.userservice.dto.request.RequestGetEighteenDto;
import com.eighteen.userservice.dto.response.ResponseGetEighteenDto;
import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.MyEighteenRepository;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MyEighteenService {

    @Autowired
    private MyEighteenRepository myEighteenRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseGetEighteenDto getEighteen(String userId, RequestGetEighteenDto requestGetEighteenDto) {

        User user = userRepository.findByUserId(userId);
        List<MyEighteen> myEighteens = myEighteenRepository.findByUser(user);

        List<MusicDto> musicDtos = new ArrayList<>();
        for (MyEighteen myEighteen : myEighteens) {
            MusicDto musicDto = new ModelMapper().map(myEighteen, MusicDto.class);
            musicDto.setIsEighteen(Boolean.TRUE);
            musicDtos.add(musicDto);
        }

        Random random = new Random();
        List<MusicDto> quicks = new ArrayList<>();
        Pageable pageable = PageRequest.of(requestGetEighteenDto.getPage(), requestGetEighteenDto.getSize());
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), musicDtos.size());
        Page<MusicDto> musicDtoPage = new PageImpl<>(musicDtos.subList(start, end), pageable, musicDtos.size());
        if (myEighteens.size() > 5) {
            for (int i = 0; i < 5; i++) {

                int randomIndex = random.nextInt(myEighteens.size());
                MyEighteen randomElement = myEighteens.get(randomIndex);
                MusicDto quick = new ModelMapper().map(randomElement, MusicDto.class);
                quick.setIsEighteen(Boolean.TRUE);
                quicks.add(quick);
            }
        }
        else {
            for (MyEighteen myEighteen : myEighteens) {
                MusicDto quick = new ModelMapper().map(myEighteen, MusicDto.class);
                quick.setIsEighteen(Boolean.TRUE);
                quicks.add(quick);
            }
        }

        ResponseGetEighteenDto responseGetEighteenDto = new ResponseGetEighteenDto(musicDtoPage, quicks);

        return responseGetEighteenDto;
    }

    public String addEighteen(String userId, RequestEighteenDto requestEighteenDto) {

        User user = userRepository.findByUserId(userId);
        Music music = requestEighteenDto.getMusic();
        MyEighteen myEighteen = MyEighteen.builder()
                .user(user)
                .music(music)
                .build();
        myEighteenRepository.save(myEighteen);
        return music.getTitle();
    }

    public String deleteEighteen(String userId, RequestEighteenDto requestEighteenDto) {

        User user = userRepository.findByUserId(userId);
        Music music = requestEighteenDto.getMusic();
        MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, music);
        myEighteenRepository.delete(myEighteen);
        return music.getTitle();
    }

}
