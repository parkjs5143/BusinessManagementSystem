package com.wish.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class BaseController {

    // main https://127.0.0.1:9090/
    @GetMapping("/")
    public String main(){
        return "board/main.html";
    }

}
