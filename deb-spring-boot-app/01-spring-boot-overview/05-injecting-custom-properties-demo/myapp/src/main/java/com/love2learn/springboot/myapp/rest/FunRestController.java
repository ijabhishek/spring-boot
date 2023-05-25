package com.love2learn.springboot.myapp.rest;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FunRestController {
    // inject properties for coach.name and team.nam
    @Value("${coach.name}")
    private String coachName;
    @Value("${team.name}")
    private String teamName;

    //expose new endpoints for "teaminfo"
    @GetMapping("/teaminfo")
    public String getTeamInfo(){
       return "Coach Name: "+ coachName + " Team Name: " + teamName;
    }


    // expose "/" that return "Hello world"

    @GetMapping("/")
    public String sayHello(){
        return " Hello Universe";
    }

}
