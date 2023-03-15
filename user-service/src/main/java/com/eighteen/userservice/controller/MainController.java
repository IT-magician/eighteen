package com.eighteen.userservice.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/test")
    public JSONObject test() throws IOException, ParseException {

        String fileName = "example.json";
        String content = Files.readString(Paths.get(fileName));

        JSONObject json = new JSONObject(content);
        System.out.println(json.toString());

        return json
    }



}
