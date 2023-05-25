package com.pyaribindu.demo.common;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {

    @Override
    public String doSpeak() {
        return "Bhow Bhow Bhow!!!!";
    }
}
