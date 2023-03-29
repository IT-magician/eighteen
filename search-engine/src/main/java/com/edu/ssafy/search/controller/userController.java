package com.edu.ssafy.search.controller;

import com.edu.ssafy.search.dto.SongDTO;
import com.edu.ssafy.search.service.UserIdxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/elastic_search")
public class userController {

    final private UserIdxService userIdxService;

    public userController(UserIdxService userIdxService) {
        this.userIdxService = userIdxService;
    }

    @PostMapping("/regist/{user_id}")
    ResponseEntity regist(@PathVariable String user_id) {
        if (user_id == null || user_id.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        userIdxService.regist(user_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/data/{user_id}")
    ResponseEntity addData(@PathVariable String user_id, @RequestBody List<SongDTO> songs) throws IOException {
        userIdxService.addData(user_id, songs);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/data/{user_id}")
    ResponseEntity deleteData(@PathVariable String user_id, @RequestBody List<SongDTO> songs) throws IOException {
        userIdxService.deleteData(user_id, songs);
        return new ResponseEntity(HttpStatus.OK);
    }

}
