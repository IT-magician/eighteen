package com.edu.ssafy.search.controller;

import com.edu.ssafy.search.dto.SongDTO;
import com.edu.ssafy.search.dto.SongWithPreferableDTO;
import com.edu.ssafy.search.service.SearchService;
import com.edu.ssafy.search.service.UserIdxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/search")
public class searchController {

    private final SearchService searchService;
    private final UserIdxService userIdxService;

    public searchController(SearchService searchService, UserIdxService userIdxService) {
        this.searchService = searchService;
        this.userIdxService = userIdxService;
    }


    @GetMapping("/title/{title}")
    List<SongDTO> searchBytitle(@PathVariable String title) throws UnsupportedEncodingException {
        title = URLDecoder.decode(title, "UTF-8");
        List<SongDTO> list = new LinkedList<>();

        try {
            list = searchService.searchBytitle(title);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        return list;
    }

    @GetMapping("/singer/{singer}")
    List<SongDTO> searchBysinger(@PathVariable String singer) throws UnsupportedEncodingException {
        singer = URLDecoder.decode(singer, "UTF-8");
        List<SongDTO> list = new LinkedList<>();

        try {
            list = searchService.searchBysinger(singer);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        return list;
    }

    @GetMapping("/title/pagination/{title}")
    ResponseEntity searchBytitle(@PathVariable String title, String user_id, Long pagination_idx, Long pagination_size) throws UnsupportedEncodingException {
        return new ResponseEntity(userIdxService.searchBytitleAndPagination(user_id, title, pagination_idx, pagination_size), HttpStatus.OK);
    }

    @GetMapping("/singer/pagination/{singer}")
    ResponseEntity searchBysinger(@PathVariable String singer, String user_id, Long pagination_idx, Long pagination_size) {
        return new ResponseEntity(userIdxService.searchBysingerAndPagination(user_id, singer, pagination_idx, pagination_size), HttpStatus.OK);

    }
}
