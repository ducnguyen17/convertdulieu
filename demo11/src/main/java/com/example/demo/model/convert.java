package com.example.demo.model;

import javax.websocket.DecodeException;

public interface convert <P, H> {
            P encode(H text);

            H decode(P byteArray) throws DecodeException;
}
