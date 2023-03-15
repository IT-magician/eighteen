package com.eighteen.userservice.controller;

import com.eighteen.userservice.dto.RequestEighteenDto;
import com.eighteen.userservice.dto.RequestGetEighteenDto;
import com.eighteen.userservice.dto.ResponseGetEighteenDto;
import com.eighteen.userservice.service.MyEighteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myEighteen")
public class MyEighteenController {

    @Autowired
    private MyEighteenService myEighteenService;

    @GetMapping("/getEighteen")
    public ResponseEntity<ResponseGetEighteenDto> getEighteen(RequestGetEighteenDto requestGetEighteenDto) {

        ResponseGetEighteenDto responseGetEighteenDto = myEighteenService.getEighteen(requestGetEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseGetEighteenDto);
    }

    @PutMapping("/add")
    public ResponseEntity<String> addEighteen(@RequestBody RequestEighteenDto requestEighteenDto) throws Exception {

        String title = myEighteenService.addEighteen(requestEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(title + "add");
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteEighteen(@RequestBody RequestEighteenDto requestEighteenDto) throws Exception {

        String title = myEighteenService.deleteEighteen(requestEighteenDto);
        return ResponseEntity.status(HttpStatus.OK).body(title + "delete");
    }

}
