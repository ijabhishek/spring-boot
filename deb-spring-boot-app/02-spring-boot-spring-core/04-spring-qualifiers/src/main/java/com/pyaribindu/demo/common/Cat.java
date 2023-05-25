package com.pyaribindu.demo.common;

import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal{
    @Override
    public String doSpeak() {
        return "Meao Meao Meao!!!";
    }
}
