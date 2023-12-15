package br.com.roberto.estudos.microservicestudents.transportlayer.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BrazilStateEnumRequest {
    AC("AC"),

    AL("AL"),

    AP("AP"),

    AM("AM"),

    BA("BA"),

    CE("CE"),

    DF("DF"),

    ES("ES"),

    GO("GO"),

    MA("MA"),

    MT("MT"),

    MS("MS"),

    MG("MG"),

    PA("PA"),

    PB("PB"),

    PR("PR"),

    PE("PE"),

    PI("PI"),

    RJ("RJ"),

    RN("RN"),

    RS("RS"),

    RO("RO"),

    RR("RR"),

    SC("SC"),

    SP("SP"),

    SE("SE"),

    TO("TO");

    private String value;

    BrazilStateEnumRequest(String value) {
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
