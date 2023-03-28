package com.eighteen.userservice.controller;

import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/test")
    public String test(){

//        for (int i = 1; i < 10; i++) {
//
//            User userEntity =
//        }

        return "success";
    }



}
