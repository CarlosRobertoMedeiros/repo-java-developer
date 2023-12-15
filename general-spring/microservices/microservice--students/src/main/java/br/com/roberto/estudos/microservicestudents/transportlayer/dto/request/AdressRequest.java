package br.com.roberto.estudos.microservicestudents.transportlayer.dto.request;

import br.com.roberto.estudos.microservicestudents.transportlayer.dto.BrazilStateEnumRequest;

public record AdressRequest(String street,
                            String city,
                            BrazilStateEnumRequest brazilState,
                            String zip,
                            String state)
{}

