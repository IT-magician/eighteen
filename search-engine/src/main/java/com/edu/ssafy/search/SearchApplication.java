package com.edu.ssafy.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.*;
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
    }
}
