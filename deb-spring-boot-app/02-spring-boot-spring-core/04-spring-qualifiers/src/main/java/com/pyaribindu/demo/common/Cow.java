package com.pyaribindu.demo.common;

import org.springframework.stereotype.Component;

@Component
public class Cow implements Animal {
    @Override
    public String doSpeak() {
        return "Maaaaa Maaaaaa!!!!";
    }
}
