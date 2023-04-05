package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseInfoDto;
import com.eighteen.userservice.dto.response.ResponseMusicDetailDto;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.service.MainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @ApiOperation(value = "유저 정보", response = ResponseMusicDetailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("")
    public ResponseEntity<?> getInfo(@RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseInfoDto responseInfoDto = mainService.getInfo(userId);
        if (responseInfoDto.getNickname() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseInfoDto);
    }



}
