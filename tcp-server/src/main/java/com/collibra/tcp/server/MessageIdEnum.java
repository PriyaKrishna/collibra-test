package com.collibra.tcp.server;

/**
 * Created by d-yw44cn on 23/03/2017.
 */
public enum MessageIdEnum {
    CLIENT_END_MESSAGE("BYE MATE!"),
    CLIENT_INPUT_MESSAGE("HI, I'M ");

    private String message;

    private MessageIdEnum(String value) {
        message = value;
    }

    public String getMessage() {
        return message;
    }
}
