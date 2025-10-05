package br.com.roberto.samplehexagonal.ms_students.infraestructure.adapter.in.rest;

import br.com.roberto.samplehexagonal.ms_students.application.port.out.StudentUseCase;
import br.com.roberto.samplehexagonal.ms_students.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/students")
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @PostMapping("/cpf/{cpf}")
    public StudentDTO create(@RequestBody StudentDTO dto){
        Student student = StudentDTO.toDomain(dto);
        return StudentDTO.fromDomain(studentUseCase.save(student));
    }

    @GetMapping("/cpf/{cpf}")
    public StudentDTO getByCpf(@PathVariable String cpf){
        Student student = studentUseCase.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Student not found with CPF: " + cpf));
        return StudentDTO.fromDomain(student);
    }

    @GetMapping
    public Page<StudentDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        var studentPage =  studentUseCase.findAll(pageable)
                .map(StudentDTO::fromDomain);

        return new PageImpl<>(
                studentPage.getContent(),
                pageable,
                studentPage.getTotalElements()
        );
    }

    @PutMapping("/cpf/{cpf}")
    public StudentDTO update(@PathVariable String cpf, @RequestBody StudentDTO dto){
        Student student = new Student(dto.id(),cpf, dto.name(), dto.email(), dto.birthDate());
        return StudentDTO.fromDomain(studentUseCase.update(student));
    }

    @DeleteMapping("/cpf/{cpf}")
    public void delete(@PathVariable String cpf){

        studentUseCase.deleteByCpf(cpf);
    }

}


//TODO:
  //Ajustar todos os rests para retornar objeto ResponseEntity e garantir todos os retornos
  //Refatorar a solução para o menor codigo possivel
  //Implementar os testes unitarios
  //Implementar o ArchUnit
  //Implementar o segundo recurso para postgres, porem tem que subir via docker
  //Implementar o Consumidor de Tópico Kafka e um produtor
  //Implementar o Consumidor de fila SQS e um consumidor
  //Fazer o microserviço de Notas
  //Realizar a chamada síncrona entre eles
  //Realizar chamada assíncrona
  //Implementar os Logs
  //Implementar as métricas com Grafana para Controller, Service e Tecnologia
  // Implementar o pacote de teste de Component com RestAssured
  //Colocar o desenho da arquitetura Hexagonal no Readme

