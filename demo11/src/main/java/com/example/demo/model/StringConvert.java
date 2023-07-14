package com.example.demo.model;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.websocket.DecodeException;
import java.nio.charset.StandardCharsets;

@Component
public class StringConvert implements convert<String , String>{
    @Override
    public String encode(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }
    @Override
    public String decode(String byteArray) throws DecodeException {
        byte[] decodedBytes = Base64.decodeBase64(byteArray.getBytes(StandardCharsets.UTF_8));
        String decodeString = new String(decodedBytes ,StandardCharsets.UTF_8);
        return decodeString;
    }
}
