package com.phg.codingexercises;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NumberController {

  @GetMapping("/javaController/health")
  public Map<String, String> returnHealthOfJavaCompilation() {
    return new HashMap<String, String>() {
      {
        put("message", "Java is being compiled too.");
      }
    };

  }
}