package com.edu.ssafy.search.controller;

import com.edu.ssafy.search.dto.SongInfoDTO;
import com.edu.ssafy.search.service.SearchService;
import com.edu.ssafy.search.service.UserIdxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/search")
public class searchController {

    private final SearchService searchService;
    private final UserIdxService userIdxService;

    private final ObjectMapper objectMapper;

    public searchController(SearchService searchService, UserIdxService userIdxService) {
        this.searchService = searchService;
        this.userIdxService = userIdxService;
        this.objectMapper = new ObjectMapper();
    }


    @GetMapping("/title/{title}")
    String searchBytitle(@PathVariable String title, @RequestParam(required=false) Map<String,String> qparams) throws UnsupportedEncodingException, JsonProcessingException {
        System.out.println("pretty : " + qparams.containsKey("pretty") + " " + qparams);


        title = URLDecoder.decode(title, "UTF-8");
        List<SongInfoDTO> list = new LinkedList<>();

        try {
            list = searchService.searchBytitle(title);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        if (qparams.containsKey("pretty")) return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

        return objectMapper.writeValueAsString(list);
    }

    @GetMapping("/singer/{singer}")
    String searchBysinger(@PathVariable String singer, @RequestParam(required=false) Map<String,String> qparams) throws UnsupportedEncodingException, JsonProcessingException {
        singer = URLDecoder.decode(singer, "UTF-8");
        List<SongInfoDTO> list = new LinkedList<>();

        try {
            list = searchService.searchBysinger(singer);
        }catch (Throwable e) {
            e.printStackTrace();
        }


        if (qparams.containsKey("pretty")) return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

        return objectMapper.writeValueAsString(list);
    }

    @GetMapping("/pagination/title/{title}")
    ResponseEntity searchBytitle(@PathVariable String title, @RequestHeader("x-forwarded-for-user-id") String user_id, Long pagination_idx, Long pagination_size, @RequestParam(required=false) Map<String,String> qparams) throws UnsupportedEncodingException, JsonProcessingException {
        if (user_id == null || user_id.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if (qparams.containsKey("pretty")) return new ResponseEntity(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userIdxService.searchBytitleAndPagination(user_id, title, pagination_idx, pagination_size)), HttpStatus.OK);

        return new ResponseEntity(objectMapper.writeValueAsString(userIdxService.searchBytitleAndPagination(user_id, title, pagination_idx, pagination_size)), HttpStatus.OK);
    }

    @GetMapping("/pagination/singer/{singer}")
    ResponseEntity searchBysinger(@PathVariable String singer, @RequestHeader("x-forwarded-for-user-id") String user_id, Long pagination_idx, Long pagination_size, @RequestParam(required=false) Map<String,String> qparams) throws JsonProcessingException {
        if (user_id == null || user_id.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if (qparams.containsKey("pretty")) return new ResponseEntity(objectMapper.writeValueAsString(userIdxService.searchBysingerAndPagination(user_id, singer, pagination_idx, pagination_size)), HttpStatus.OK);

        return new ResponseEntity(objectMapper.writeValueAsString(userIdxService.searchBysingerAndPagination(user_id, singer, pagination_idx, pagination_size)), HttpStatus.OK);

    }

    @GetMapping("/eighteen/pagination/title/{title}")
    ResponseEntity searchBytitleWithPreferable(@PathVariable String title, @RequestHeader("x-forwarded-for-user-id") String user_id, Long pagination_idx, Long pagination_size, @RequestParam(required=false) Map<String,String> qparams) throws UnsupportedEncodingException, JsonProcessingException {
        if (user_id == null || user_id.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Map<String, Object> map = userIdxService.searchBytitleAndPaginationWithPreferable(user_id, title, pagination_idx, pagination_size);

        if (qparams.containsKey("pretty")) return new ResponseEntity(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map), HttpStatus.OK);

        return new ResponseEntity(objectMapper.writeValueAsString(map), HttpStatus.OK);
    }

    @GetMapping("/eighteen/pagination/singer/{singer}")
    ResponseEntity searchBysingerWithPreferable(@PathVariable String singer, @RequestHeader("x-forwarded-for-user-id") String user_id, Long pagination_idx, Long pagination_size, @RequestParam(required=false) Map<String,String> qparams) throws JsonProcessingException {
        if (user_id == null || user_id.isEmpty()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if (qparams.containsKey("pretty")) return new ResponseEntity(objectMapper.writeValueAsString(userIdxService.searchBysingerAndPaginationWithPreferable(user_id, singer, pagination_idx, pagination_size)), HttpStatus.OK);

        return new ResponseEntity(objectMapper.writeValueAsString(userIdxService.searchBysingerAndPaginationWithPreferable(user_id, singer, pagination_idx, pagination_size)), HttpStatus.OK);

    }
}
