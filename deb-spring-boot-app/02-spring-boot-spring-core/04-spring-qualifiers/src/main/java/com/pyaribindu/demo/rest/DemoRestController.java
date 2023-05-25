package com.pyaribindu.demo.rest;

import com.pyaribindu.demo.common.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    private Animal myDog;
    private Animal myCat;
    private Animal myCow;

    @Autowired
    public DemoRestController(@Qualifier("dog") Animal myDog, @Qualifier("cat") Animal myCat, @Qualifier("cow") Animal myCow){
        this.myDog = myDog;
        this.myCat = myCat;
        this.myCow = myCow;
    }

    @GetMapping("/catSpeak")
    public String catSpeak(){
        return myCat.doSpeak();
    }
    @GetMapping("/dogSpeak")
    public String dogSpeak(){
        return myDog.doSpeak();
    }
    @GetMapping("/cowSpeak")
    public String cowSpeak(){
        return myCow.doSpeak();
    }
}
