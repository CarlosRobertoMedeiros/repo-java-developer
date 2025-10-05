package br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.out.persistence;

import br.com.roberto.samplehexagonal.ms_students.domain.model.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tbl_Students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String name;
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public static StudentEntity fromDomain(Student student) {
        StudentEntity entity = new StudentEntity();
        entity.id = student.id();
        entity.cpf = student.cpf();
        entity.name = student.name();
        entity.email = student.email();
        entity.birthDate = student.birthDate();
        return entity;
    }

    public Student toDomain() {
        return new Student(id, cpf, name, email, birthDate);
    }

    public StudentEntity toEntity() {
        return this;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
