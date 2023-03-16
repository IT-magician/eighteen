package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.request.RequestUpdateProfileDto;
import com.eighteen.userservice.dto.response.ResponseProfileDto;
import com.eighteen.userservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getProfile")
    public ResponseEntity<ResponseProfileDto> getProfile(@RequestParam("userId") String userId) {

        ResponseProfileDto responseProfileDto = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseProfileDto);
    }

    @GetMapping("/checkNickname")
    public ResponseEntity<String> checkNickname(@RequestParam("userId") String userId, @RequestParam("nickname") String nickname) {

        String res = profileService.checkNickname(userId, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(RequestUpdateProfileDto requestUpdateProfileDto) {

        String res = profileService.updateProfile(requestUpdateProfileDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/updateImage")
    public ResponseEntity<String> updateImage(@RequestParam("userId") String userId, @RequestParam("profileImage") MultipartFile profileImage) throws IOException {

        String res = profileService.updateImage(userId, profileImage);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
