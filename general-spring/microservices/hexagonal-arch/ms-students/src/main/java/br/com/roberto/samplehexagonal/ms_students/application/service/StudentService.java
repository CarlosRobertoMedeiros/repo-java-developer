package br.com.roberto.samplehexagonal.ms_students.application.service;

import br.com.roberto.samplehexagonal.ms_students.application.port.out.StudentUseCase;
import br.com.roberto.samplehexagonal.ms_students.application.service.exceptions.StudentNotFoundException;
import br.com.roberto.samplehexagonal.ms_students.domain.model.Student;
import br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.out.persistence.StudentEntity;
import br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.out.persistence.StudentJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements StudentUseCase {

    private final StudentJpaRepository studentJpaRepository;

    public StudentService(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public Student save(Student student) {

        Optional<StudentEntity> existingStudent = studentJpaRepository.findByCpf(student.cpf());

        if (existingStudent.isPresent()){
            return existingStudent.get().toDomain();
        }

        student.validateStudentIsUnderAge();
        StudentEntity studentEntity =  studentJpaRepository.save(StudentEntity.fromDomain(student));
        //studentEntity.setId(null);
        return this.studentJpaRepository.save(studentEntity).toDomain();
    }

    @Override
    public Student update(Student student) {

        Student existingStudent = studentJpaRepository.findByCpf(student.cpf())
                .map(StudentEntity::toDomain)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("Student not found with CPF: %s ",student.cpf())));


        student.validateStudentIsUnderAge();

        StudentEntity entityToUpdate = StudentEntity.fromDomain(student);
        entityToUpdate.setId(studentJpaRepository.findByCpf(student.cpf())
                .map(StudentEntity::getId)
                .orElse(null));

        return studentJpaRepository.save(entityToUpdate).toDomain();
    }

    @Override
    public Optional<Student> findByCpf(String cpf) {
        return studentJpaRepository.findByCpf(cpf).map(StudentEntity::toDomain);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentJpaRepository.findAll(pageable)
                .map(StudentEntity::toDomain);
    }

    @Override
    @Transactional
    public void deleteByCpf(String cpf) {
        var studentExistent =  this.findByCpf(cpf);
        if (studentExistent.isPresent()){
            studentJpaRepository.deleteByCpf(cpf);
        }
    }
}
