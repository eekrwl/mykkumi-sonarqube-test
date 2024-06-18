package com.swmarastro.mykkumiserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<HelloResponse> home() {
        return ResponseEntity.ok(HelloResponse.builder()
                .title("Hello World!")
                .build());
    }
}
