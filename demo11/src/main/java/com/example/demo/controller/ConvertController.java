package com.example.demo.controller;
import com.example.demo.load.TransRequest;
import com.example.demo.model.ConvertAdapter;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConvertController {
    private final ConvertAdapter convertAdapter;

    public ConvertController(ConvertAdapter convertAdapter) {
        this.convertAdapter = convertAdapter;

    }
    public static String convertByteArrayToHexString(byte[] byteArray) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : byteArray) {
            hexStringBuilder.append(String.format("%02x ", b));
        }
        return hexStringBuilder.toString().trim();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{from}/{to}")
    ResponseEntity<Object> convert(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to,
                                   @RequestBody TransRequest transRequest) throws DecoderException {
        String textData = transRequest.getData();
        String result = null;
        switch (from) {
            case "String": {
                if (to.equals("Base64")) {
                    result = convertAdapter.convertStringToBase64(textData);
                } else if (to.equals("Hex")) {
                    result = convertAdapter.convertStringToHexa(textData);
                } else if (to.equals("Byte")) {
                    result = convertByteArrayToHexString(convertAdapter.convertStringToByte(textData));
                } else if (to.equals("String")) {
                    result = convertAdapter.convertStringToString(textData);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kiểu dữ liệu đầu vào khong chính xác");
                }
            }
            break;
            case "Base64": {
                if (to.equals("Hex")) {
                    result = convertAdapter.convertBase64ToHexa(textData);
                } else if (to.equals("String")) {
                    result = convertAdapter.convertBase64ToString(textData);
                } else if (to.equals("Byte")) {
                    result = convertByteArrayToHexString(convertAdapter.convertBase64ToByte(textData));
                }else if (to.equals("Base64")) {
                    result = convertAdapter.convertStringToString(textData);
                }  else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kiểu dữ liệu đầu vào khong chính xác");
                }
            }
            break;
            case "Hex": {
                if (to.equals("Base64")) {
                    result = convertAdapter.convertHexaToBase64(textData);
                } else if (to.equals("String")) {
                    result = convertAdapter.convertHexadecimalToString(textData);
                } else if (to.equals("Byte")) {
                    result = convertByteArrayToHexString(convertAdapter.convertHexadecimalToByteArray(textData));
                }else if (to.equals("Hex")) {
                    result = convertAdapter.convertStringToString(textData);
                } else{
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kiểu dữ liệu đầu vào không chính xác");
                    }
                } break;
                case "Byte": {
                    if (to.equals("Hex")) {
                        result = ConvertAdapter.convertByteArrayToHexa(textData.getBytes());
                    } else if (to.equals("String")) {
                        result = ConvertAdapter.convertByteArrayToString(textData);
                    } else if (to.equals("Base64")) {
                        result = convertAdapter.convertByteToBase64(textData);
                    }else if (to.equals("Byte")) {
                        result = convertAdapter.convertStringToString(textData);
                    }else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kiểu dữ liệu đầu vào khong chính xác");
                    }
                }
                break;
                default:
                    throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
            }
            return ResponseEntity.ok().body(Map.of("result", result));
        }

    }

