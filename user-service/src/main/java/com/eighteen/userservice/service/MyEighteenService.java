package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.RequestEighteenDto;
import com.eighteen.userservice.dto.RequestGetEighteenDto;
import com.eighteen.userservice.dto.ResponseGetEighteenDto;
import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.MyEighteenRepository;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public ResponseGetEighteenDto getEighteen(RequestGetEighteenDto requestGetEighteenDto) {

        PageRequest pageRequest = PageRequest.of(requestGetEighteenDto.getPage(), requestGetEighteenDto.getSize());
        User user = userRepository.findByUserId(requestGetEighteenDto.getUserId());
        List<MyEighteen> myEighteens = myEighteenRepository.findByUser(user);
        Page<MyEighteen> myEighteensPaging = myEighteenRepository.findByUserAndMusicByOrderByMusicIdDesc(pageRequest);

        Random random = new Random();

        List<MyEighteen> quicks = new ArrayList<>();

        if (myEighteens.size() > 5) {
            for (int i = 0; i < 5; i++) {

                int randomIndex = random.nextInt(myEighteens.size());
                MyEighteen randomElement = myEighteens.get(randomIndex);
                quicks.add(randomElement);
            }
        }
        else {
            quicks = myEighteens;
        }

        ResponseGetEighteenDto responseGetEighteenDto = ResponseGetEighteenDto.builder()
                .myEighteens(myEighteens)
                .quicks(quicks)
                .build();

        return responseGetEighteenDto;
    }

    public String addEighteen(RequestEighteenDto requestEighteenDto) {

        User user = userRepository.findByUserId(requestEighteenDto.getUserId());
        Music music = requestEighteenDto.getMusic();
        MyEighteen myEighteen = MyEighteen.builder()
                .user(user)
                .music(music)
                .build();
        myEighteenRepository.save(myEighteen);
        return music.getTitle();
    }

    public String deleteEighteen(RequestEighteenDto requestEighteenDto) {

        User user = userRepository.findByUserId(requestEighteenDto.getUserId());
        Music music = requestEighteenDto.getMusic();
        MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, music);
        myEighteenRepository.delete(myEighteen);
        return music.getTitle();
    }

}
