package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseMusicDetailDto;
import com.eighteen.userservice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music/{musicId}")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/")
    public ResponseEntity<ResponseMusicDetailDto> getMusicDetail(@PathVariable("musicId") Integer musicId, @RequestParam("userId") String userId) {
//        유튜브 링크는 아직
        ResponseMusicDetailDto responseMusicDetailDto = musicService.getMusicDetail(musicId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMusicDetailDto);
    }
}
