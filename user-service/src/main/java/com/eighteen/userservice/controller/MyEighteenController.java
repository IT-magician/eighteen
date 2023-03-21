package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.request.RequestEighteenDto;
import com.eighteen.userservice.dto.request.RequestGetEighteenDto;
import com.eighteen.userservice.dto.response.ResponseGetEighteenDto;
import com.eighteen.userservice.service.MyEighteenService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myEighteen")
@Api(value = "애창곡", description = "애창곡 관련 API")
public class MyEighteenController {

    @Autowired
    private MyEighteenService myEighteenService;

    @ApiOperation(value = "애창곡 가져오기", response = ResponseGetEighteenDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/getEighteen")
    public ResponseEntity<ResponseGetEighteenDto> getEighteen(@ApiParam(value = "애창곡 페이지", required = true) @RequestBody RequestGetEighteenDto requestGetEighteenDto) {

        ResponseGetEighteenDto responseGetEighteenDto = myEighteenService.getEighteen(requestGetEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseGetEighteenDto);
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
    @PostMapping("/add")
    public ResponseEntity<String> addEighteen(@ApiParam(value = "유저아이디, 음악", required = true) @RequestBody RequestEighteenDto requestEighteenDto) throws Exception {

        String title = myEighteenService.addEighteen(requestEighteenDto);
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
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEighteen(@ApiParam(value = "유저아이디, 음악", required = true) @RequestBody RequestEighteenDto requestEighteenDto) throws Exception {

        String title = myEighteenService.deleteEighteen(requestEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(title + "delete");
    }

}
