package br.com.roberto.samplehexagonal.ms_students.domain.model;

import br.com.roberto.samplehexagonal.ms_students.domain.exception.StudentInvalidException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public record Student(
        Long id,
        String cpf,
        String name,
        String email,
        LocalDate birthDate
) {

    private static final int AGE_OF_ADULTHOOD = 18;

    public Student {
        Objects.requireNonNull(cpf, "Student cpf must not be null");
        Objects.requireNonNull(name, "Student name must not be null");
        Objects.requireNonNull(email, "Student email must not be null");
        Objects.requireNonNull(birthDate, "Student birth date must not be null");
    }

    public void validateStudentIsUnderAge(){
        if (isAdult())
            throw new StudentInvalidException(
                    String.format("Student must be under %s years old", AGE_OF_ADULTHOOD));
    }

    public int getAge(){
        return Period.between(birthDate,LocalDate.now()).getYears();
    }

    private boolean isAdult(){
        return getAge()  >= AGE_OF_ADULTHOOD;
    }
}
