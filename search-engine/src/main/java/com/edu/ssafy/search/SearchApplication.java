package com.edu.ssafy.search;

import com.edu.ssafy.search.util.WordShapeSimilarityAnalyzer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.*;
import org.apache.commons.text.similarity.LevenshteinDistance;
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


    @Override
    public void run(String... args) throws Exception {
        String lhs = "따뜻한";
        String rhs = "따뜻운";
        int maxLen = lhs.length() > lhs.length() ? lhs.length() : rhs.length();

        LevenshteinDistance ld = new LevenshteinDistance();

        double result = 0;
        double temp = ld.apply(lhs, rhs);
        result = (maxLen - temp) / maxLen;

        System.out.println(result);
    }
}
