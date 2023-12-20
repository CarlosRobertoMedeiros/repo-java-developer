package br.com.roberto.estudos.microservicestudents.core.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {
    MALE("MALE"),

    FEMALE("FEMALE");

    private String value;

    GenderEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
