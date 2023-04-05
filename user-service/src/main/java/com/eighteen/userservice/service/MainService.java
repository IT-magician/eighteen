package com.eighteen.userservice.service;

import com.eighteen.userservice.dto.response.ResponseInfoDto;
import com.eighteen.userservice.dto.response.ResponseMusicDetailDto;
import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MusicFeature;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UserRepository userRepository;

    public ResponseInfoDto getInfo(String userId) {

        User user = userRepository.findByUserId(userId);
        ResponseInfoDto responseInfoDto = new ResponseInfoDto(user);
        return responseInfoDto;
    }
}
