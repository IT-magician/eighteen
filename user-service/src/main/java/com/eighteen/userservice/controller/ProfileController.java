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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/profile")
@Api(value = "프로필", description = "프로필 관련 API")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @ApiOperation(value = "프로필 가져오기", response = ResponseProfileDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/getProfile")
    public ResponseEntity<ResponseProfileDto> getProfile(@ApiParam(value = "사용자의 ID", required = true) @RequestParam("userId") String userId) {

        ResponseProfileDto responseProfileDto = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseProfileDto);
    }

    @ApiOperation(value = "닉네임 중복확인", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/checkNickname")
    public ResponseEntity<String> checkNickname(@ApiParam(value = "사용자의 ID", required = true) @RequestParam("userId") String userId,
                                                @ApiParam(value = "입력 닉네임", required = true) @RequestParam("nickname") String nickname) {

        String res = profileService.checkNickname(userId, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ApiOperation(value = "프로필 업데이트", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@ApiParam(value = "변경프로필정보", required = true) @RequestBody RequestUpdateProfileDto requestUpdateProfileDto) {

        String res = profileService.updateProfile(requestUpdateProfileDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ApiOperation(value = "프로필사진 업데이트", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/updateImage")
    public ResponseEntity<String> updateImage(@ApiParam(value = "사용자의 Id", required = true) @RequestParam("userId") String userId,
                                              @ApiParam(value = "프로필 이미지", required = true) @RequestParam("profileImage") MultipartFile profileImage) throws IOException {

        String res = profileService.updateImage(userId, profileImage);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
