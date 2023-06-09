package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.request.RequestEighteenDto;
import com.eighteen.userservice.dto.response.ResponseGetEighteenDto;
import com.eighteen.userservice.dto.response.ResponseRandomDto;
import com.eighteen.userservice.service.MyEighteenService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/my_eighteen")
@Api(value = "애창곡", description = "애창곡 관련 API")
public class MyEighteenController {

    private final MyEighteenService myEighteenService;

    @ApiOperation(value = "애창곡 가져오기", response = ResponseGetEighteenDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("")
    public ResponseEntity<ResponseGetEighteenDto> getEighteen(@RequestHeader("x-forwarded-for-user-id") String userId,
                                                              @RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        ResponseGetEighteenDto responseGetEighteenDto = myEighteenService.getEighteen(userId, page, size);
        if (responseGetEighteenDto.getMusicPage() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseGetEighteenDto);
    }

    @ApiOperation(value = "애창곡 가져오기", response = ResponseGetEighteenDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/random")
    public ResponseEntity<ResponseRandomDto> getRandom(@RequestHeader("x-forwarded-for-user-id") String userId) {

        ResponseRandomDto responseRandomDto = myEighteenService.getRandom(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseRandomDto);
    }

    @ApiOperation(value = "애창곡 추가", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("")
    public ResponseEntity<String> addEighteen(@RequestHeader("x-forwarded-for-user-id") String userId,
                                              @ApiParam(value = "음악", required = true)@RequestBody RequestEighteenDto requestEighteenDto) throws Exception {

        String title = myEighteenService.addEighteen(userId, requestEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(title + "add");
    }

    @ApiOperation(value = "애창곡 제거", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("")
    public ResponseEntity<?> deleteEighteen(@RequestHeader("x-forwarded-for-user-id") String userId,
                                            @ApiParam(value = "음악리스트", required = true)@RequestParam("musics") List<Integer> musics) throws Exception {

        myEighteenService.deleteEighteen(userId, musics);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
