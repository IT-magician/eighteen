package com.edu.ssafy.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.Document;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SearchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://j8b304.p.ssafy.io:9200")
            .build();

    @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
    static class tmp {
        int id;
        String title;
        String singer;
        String youtube_url;
    }

    public static double findSimilarity(String x, String y) {

        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            // 필요한 경우 선택적으로 대소문자를 무시합니다.
            return (maxLength - StringUtils.getLevenshteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }


    @Override
    public void run(String... args) throws Exception {
        String target = "ㅇㄹㄴ";

    }
}
