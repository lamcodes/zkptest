package com.example.zkptest.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author zkp
 * @Date 2022-10-07 17:09
 */
@RestController
@Slf4j
public class MyController {

    @Value("${name}")
    private String dename;
    @GetMapping("/test")
    public String test(){
        log.info("配置"+dename);
        return dename;
    }
}
