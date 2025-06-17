package com.BookshelfApi.api.test;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> Test() {
        return ResponseEntity.ok("Yeah");
    }
}
