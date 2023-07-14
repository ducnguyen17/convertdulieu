package com.example.demo.controller;

import com.example.demo.model.StringConvert;
import org.springframework.web.bind.annotation.*;

import javax.websocket.DecodeException;


@RestController
@RequestMapping("/api/test")
public class test {
 private final StringConvert stringConvert;

    public test(StringConvert stringConvert) {
        this.stringConvert = stringConvert;
    }
    @PostMapping("/StringToBase64")
    public String encode(@RequestBody String s){
        return stringConvert.encode(s);
    }
    @PostMapping("/Base64ToString")
    public String decode(@RequestBody String s) throws DecodeException {
        return stringConvert.decode(s);
    }
}
