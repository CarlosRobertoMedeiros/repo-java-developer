package br.com.roberto.samplehexagonal.ms_students.application.port.out;

import br.com.roberto.samplehexagonal.ms_students.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentUseCase {
    Student save(Student student);
    Student update(Student student);
    Optional<Student> findByCpf(String cpf);
    Page<Student> findAll(Pageable pageable);
    void deleteByCpf(String cpf);
}
