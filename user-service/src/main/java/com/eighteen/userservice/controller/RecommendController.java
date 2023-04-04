package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.*;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.repository.MyEighteenRepository;
import com.eighteen.userservice.service.RecommendService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;
    private final MyEighteenRepository myEighteenRepository;

    @GetMapping("/emotion")
    public ResponseEntity<ResponseRecommendDto> getEMusicList(
            @RequestParam("emotionId") Integer emotionId,
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseRecommendDto responseRecommendDto = recommendService.getEMusicList(userId, emotionId);
        return new ResponseEntity<>(responseRecommendDto, HttpStatus.OK);
    }

    @GetMapping("/situation")
    public ResponseEntity<ResponseRecommendDto> getSMusicList(
            @RequestParam("situationId") Integer situationId,
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseRecommendDto responseRecommendDto = recommendService.getSMusicList(userId, situationId);
        return new ResponseEntity<>(responseRecommendDto, HttpStatus.OK);
    }

    @GetMapping("/weather")
    public ResponseEntity<ResponseRecommendDto> getWMusicList(
            @RequestParam("weatherId") Integer weatherId,
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseRecommendDto responseRecommendDto = recommendService.getWMusicList(userId, weatherId);
        return new ResponseEntity<>(responseRecommendDto, HttpStatus.OK);
    }

    @GetMapping("/my_eighteen")
    public ResponseEntity<ResponseRecommendDto> getEighteenRecommend(
            @RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseRecommendDto responseRecommendDto = recommendService.getEighteenRecommend(userId);
        if (responseRecommendDto == null) {
            return new ResponseEntity<>(responseRecommendDto, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseRecommendDto, HttpStatus.OK);
    }
}