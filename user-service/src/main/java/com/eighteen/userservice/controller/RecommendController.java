package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseEMusicDto;
import com.eighteen.userservice.dto.response.ResponseSMusicDto;
import com.eighteen.userservice.dto.response.ResponseWMusicDto;
import com.eighteen.userservice.service.EMusicService;
import com.eighteen.userservice.service.SMusicService;
import com.eighteen.userservice.service.WMusicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final EMusicService eMusicService;

    @GetMapping("/emotion")
    public ResponseEntity<ResponseEMusicDto> getEMusicList(
            @RequestParam("emotionId") Integer emotionId,
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseEMusicDto responseEMusicDto = eMusicService.getEMusicList(userId, emotionId);
        return new ResponseEntity<>(responseEMusicDto, HttpStatus.OK);
    }
}