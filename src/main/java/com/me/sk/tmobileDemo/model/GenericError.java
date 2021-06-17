package com.me.sk.tmobileDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericError {
    private int code;
    private String msg;

    public GenericError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
