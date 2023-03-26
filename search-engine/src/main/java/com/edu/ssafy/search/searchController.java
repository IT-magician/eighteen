package com.edu.ssafy.search;

import com.edu.ssafy.search.dto.HitSong;
import com.edu.ssafy.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/search")
public class searchController {

    private final SearchService searchService;

    public searchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/title/{title}")
    List<HitSong> searchBytitle(@PathVariable String title) throws UnsupportedEncodingException {
        title = URLDecoder.decode(title, "UTF-8");
        List<HitSong> list = new LinkedList<>();

        try {
            list = searchService.searchBytitle(title);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        return list;
    }

    @GetMapping("/singer/{singer}")
    List<HitSong> searchBysinger(@PathVariable String singer) throws UnsupportedEncodingException {
        singer = URLDecoder.decode(singer, "UTF-8");
        List<HitSong> list = new LinkedList<>();

        try {
            list = searchService.searchBysinger(singer);
        }catch (Throwable e) {
            e.printStackTrace();
        }

        return list;
    }
}
