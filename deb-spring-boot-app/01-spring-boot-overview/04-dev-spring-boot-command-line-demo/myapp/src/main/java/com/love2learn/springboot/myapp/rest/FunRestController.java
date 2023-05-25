package com.love2learn.springboot.myapp.rest;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FunRestController {

    // expose "/" that return "Hello world"

    @GetMapping("/")
    public String sayHello(){
        return " Hello Universe";
    }
}
