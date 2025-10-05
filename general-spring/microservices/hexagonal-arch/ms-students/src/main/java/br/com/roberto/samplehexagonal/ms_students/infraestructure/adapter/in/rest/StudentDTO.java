package br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.in.rest;

import br.com.roberto.samplehexagonal.ms_students.domain.model.Student;

import java.time.LocalDate;

public record StudentDTO(Long id, String cpf, String name, String email, LocalDate birthDate) {

    public static StudentDTO fromDomain(Student student){
        return new StudentDTO(student.id(),student.cpf(), student.name(), student.email(), student.birthDate());
    }

    public static Student toDomain(StudentDTO studentDTO) {
        return new Student(studentDTO.id(), studentDTO.cpf(), studentDTO.name, studentDTO.email(), studentDTO.birthDate());
    }
}