package com.me.sk.tmobileDemo.model.error;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenericException extends RuntimeException{
    private int code;
    private String msg;

    public GenericException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
