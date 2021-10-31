package com.geekcolab.development.SpringAspectSample.service;

import com.geekcolab.development.SpringAspectSample.aspect.Loggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Value("${app.example.text}")
    private String someText;

    public ExampleService(){
        super();
    }

    @Loggable
    public String getSomeText(String moreText) {
        return this.someText + " " + moreText;
    }

}
