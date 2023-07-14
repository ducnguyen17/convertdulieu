//package com.example.demo.controller;
//
//import com.example.demo.load.TransRequest;
//import com.example.demo.model.Bytes;
//import com.fasterxml.jackson.databind.DatabindException;
//import org.springframework.core.codec.CodecException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.websocket.DecodeException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/test")
//public class testController {
//    private final Bytes bytes;
//
//    public testController(Bytes bytes) {
//        this.bytes = bytes;
//    }
//
//
//    @PostMapping(value = "/{from}/{to}")
//    ResponseEntity<Object> convert(@PathVariable(value = "from") String from , @PathVariable(value = "to") String to , @RequestBody TransRequest transRequest) throws DecodeException, DatabindException {
//        String textdata = transRequest.getData();
//        String result = null;
//        switch (from){
//            case "String" : {
//                if (to.equals("Byte")){
//                    result = test.convertStringToByte(textdata);
//                }else if(to.equals("Base64")){
//                    result = test.convertStringToBase64(textdata);
//                } else if (to.equals("Hex")) {
//                    result = test.convertStringToHex(textdata);
//                }
//            }
//            case "Hex" : {
//                if (to.equals("String")){
//                    result = test.convertHexToString(textdata);
//                } else if (to.equals("Base64")) {
//                    result = test.convertHexToBase64(textdata);
//                }
//
//            }
//            case "Base64" : {
//                if (to.equals("String")){
//                    result = test.convertBase64ToString(textdata);
//                } else if (to.equals("Hex")) {
//                    result = test.convertBase64ToHex(textdata);
//                }
//            }
//            case "Byte" : {
//
//            }
//            break;
//            default:throw new CodecException("dữ liêu chuyển đôi ko hợp lệ");
//        }
//        return ResponseEntity.ok().body(Map.of("result" , result));
//    }
//}
