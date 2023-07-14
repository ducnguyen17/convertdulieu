package com.example.demo.model;

import lombok.Data;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;

import javax.websocket.DecodeException;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Configuration
@Data
public class ConvertAdapter {
    //Convert Base64 -> Byte Array

    public byte[] convertBase64ToByte(String base64String) {

        byte[] byteArray = null;
        try {
            Base64 base64 = new Base64();
            byteArray = base64.decode(base64String);
        } catch (IllegalArgumentException e) {
            // Handle exception here
        }
        return byteArray;
    }

    // hex -> byte
    public byte[] convertHexadecimalToByteArray(String hexToByte) {
        int len = hexToByte.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            String hex = hexToByte.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(hex, 16);
        }
        return bytes;
    }

    public String convertBase64ToString(String base64String) throws DecoderException {
        byte[] decodedBytes = Base64.decodeBase64(base64String.getBytes(StandardCharsets.UTF_8));
        String decodeString = new String(decodedBytes, StandardCharsets.UTF_8);
        return decodeString;
    }

    public static String convertByteArrayToString(String input) {
        String[] strings = input.split(" ");
        byte[] bytes = new byte[strings.length];
        for (int i = 0 ; i < strings.length; i++){
            bytes[i] = (byte)Integer.parseInt(strings[i] , 16);

        }
        return new String(bytes , StandardCharsets.UTF_8);

    }

    //Convert Hexadecimal -> String
    public static String convertHexadecimalToString(String hexaString) throws DecoderException {
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseHexBinary(hexaString);
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    public static String convertStringToHexa(String stringToHexa) {
        byte[] bytes = stringToHexa.getBytes(StandardCharsets.UTF_8);
        String hex = javax.xml.bind.DatatypeConverter.printHexBinary(bytes);
        return hex;
    }

    public static String convertByteArrayToHexa(byte[] byteArrayToHexa) {
        BigInteger bigInteger = new BigInteger(1, byteArrayToHexa);
        String hexString = bigInteger.toString(16);
        if (hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    //Convert String -> Base64
    public static String convertStringToBase64(String stringToBase) {
        byte[] bytes = stringToBase.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    //Convert Base64 -> Hexadecimal
    public static String convertBase64ToHexa(String baseToHex) throws DecoderException {
        byte[] decodedBytes = Base64.decodeBase64(baseToHex);
        String hex = DatatypeConverter.printHexBinary(decodedBytes);
        return hex;
    }

    //Convert Hexadecimal -> Base64
    public static String convertHexaToBase64(String hexaToBase) throws DecoderException {
        byte[] bytes = DatatypeConverter.parseHexBinary(hexaToBase);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    //Convert String -> Byte
    public static byte[] convertStringToByte(String stringToByte) throws DecoderException {
        byte[] decodedBytes = stringToByte.getBytes();
        return decodedBytes;
    }

    public String convertStringToString(String inputString) {
        String outputString = inputString;
        return outputString;
    }

    //byte to base 64
    public String convertByteToBase64(String base64String) {
        byte[] bytes = base64String.getBytes();
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }
}

    //base64 to hex
//    public String convertBase64ToHex(String base64String) {
//        String hexString = "";
//        try {
//            byte[] byteArray = Base64.decodeBase64(base64String);
//            StringBuilder hexBuilder = new StringBuilder();
//            for (byte b : byteArray) {
//                hexBuilder.append(String.format("%02X", b));
//            }
//            hexString = hexBuilder.toString();
//        } catch (IllegalArgumentException e) {
//            // Handle exception here
//        }
//        return hexString;
//    }

    // Convert byte array to string
//    public String convertByteArrayToString(byte[] byteArrayToString) {
//        String hexString = convertByteArrayToHexadecimal(byteArrayToString);
//        return convertHexadecimalToString(hexString);
//    }
