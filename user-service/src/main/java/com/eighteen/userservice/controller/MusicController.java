package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseGetEighteenDto;
import com.eighteen.userservice.dto.response.ResponseMusicDetailDto;
import com.eighteen.userservice.service.MusicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music/{musicId}")
@Api(value = "음악", description = "음악 관련 API")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @ApiOperation(value = "음악 세부정보", response = ResponseMusicDetailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("")
    public ResponseEntity<ResponseMusicDetailDto> getMusicDetail(@ApiParam(value = "음악 id", required = true) @PathVariable("musicId") Integer musicId,
                                                                 @RequestHeader("x-for-warded-for-user-id") String userId) {
//        유튜브 링크는 아직
        ResponseMusicDetailDto responseMusicDetailDto = musicService.getMusicDetail(musicId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMusicDetailDto);
    }
}
