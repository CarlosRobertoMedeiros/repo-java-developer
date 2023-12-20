package br.com.roberto.estudos.microservicestudents.core.entities;


import java.util.List;

public class Student {
    private String name;
    private String dateBirth;
    private String mothersName;
    private String fathersName;
    private GenderEnum gender;
    private List<Address> addressList;
}
