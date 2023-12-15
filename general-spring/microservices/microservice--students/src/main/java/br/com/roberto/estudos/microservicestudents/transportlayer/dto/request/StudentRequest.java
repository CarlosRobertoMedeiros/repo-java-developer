package br.com.roberto.estudos.microservicestudents.transportlayer.dto.request;

import java.util.List;

public record StudentRequest(String name,
                             String dateBirth,
                             String mothersName,
                             String fathersName,
                             String gender,
                             List<AdressRequest> addressList
                              )
{}
