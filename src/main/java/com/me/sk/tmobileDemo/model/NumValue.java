package com.me.sk.tmobileDemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NumValue {
    private int value;

    public NumValue(int value) {
        this.value = value;
    }
}
