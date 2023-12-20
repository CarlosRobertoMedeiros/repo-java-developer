package br.com.roberto.estudos.microservicestudents.core.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StateEnum {
    A("A"),
    I("I");

    private String value;

    private StateEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
