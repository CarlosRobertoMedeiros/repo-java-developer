package br.com.roberto.samplehexagonal.ms_students.domain.exception;

public class StudentInvalidException extends RuntimeException{
    public StudentInvalidException(String message) {
        super(message);
    }
}
