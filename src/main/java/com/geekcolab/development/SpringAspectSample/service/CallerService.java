package com.geekcolab.development.SpringAspectSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallerService {

    private ExampleService exampleService;

    @Autowired
    public CallerService(ExampleService exampleService) {
        this.exampleService = exampleService;
        this.exampleService.getSomeText("! Thank you!");
    }

}
