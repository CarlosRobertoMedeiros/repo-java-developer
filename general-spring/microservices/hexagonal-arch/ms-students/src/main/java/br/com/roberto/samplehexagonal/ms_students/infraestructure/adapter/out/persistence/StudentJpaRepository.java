package br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByCpf(String cpf);
    void deleteByCpf(String cpf);
    Page<StudentEntity> findAll(Pageable pageable);


}
