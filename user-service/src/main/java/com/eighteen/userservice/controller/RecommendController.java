package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseEMusicDto;
import com.eighteen.userservice.service.EReccMusicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final EReccMusicService eReccMusicService;


    @GetMapping("/emotion")
    public ResponseEntity<ResponseEMusicDto> getRecommendedMusicList(
            @RequestParam("emotionId") Integer emotionId,
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseEMusicDto responseEMusicDto = eReccMusicService.getRecommendedMusicList(userId, emotionId);
        return new ResponseEntity<>(responseEMusicDto, HttpStatus.OK);
    }
}