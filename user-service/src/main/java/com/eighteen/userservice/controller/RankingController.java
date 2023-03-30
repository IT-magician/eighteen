package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.response.ResponseProfileDto;
import com.eighteen.userservice.dto.response.ResponseRankingDto;
import com.eighteen.userservice.service.RankingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ranking")
@Api(value = "랭킹", description = "랭킹 관련 API")
public class RankingController {

    private final RankingService rankingService;

    @ApiOperation(value = "랭킹 가져오기", response = ResponseRankingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("")
    public ResponseEntity<?> getRanking(@RequestHeader("x-forwarded-for-user-id") String userId,
                                        @RequestParam("gender") String gender, @RequestParam("age") Integer age) {

        ResponseRankingDto responseRankingDto = rankingService.getRanking(userId, gender, age);
        return ResponseEntity.status(HttpStatus.OK).body(responseRankingDto);
    }
}
