package br.com.roberto.samplehexagonal.ms_students.application.service.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}
