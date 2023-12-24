package br.com.roberto.estudos.microservicestudents.transportlayer.controllers.impl;



import br.com.roberto.estudos.microservicestudents.core.entities.Student;
import br.com.roberto.estudos.microservicestudents.core.interactors.StudentsUseCase;
import br.com.roberto.estudos.microservicestudents.transportlayer.controllers.StudentsApi;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.StudentRequest;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.StudentResponse;
import br.com.roberto.estudos.microservicestudents.transportlayer.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentsController implements StudentsApi {

    private static final Logger log = LoggerFactory.getLogger(StudentsController.class);

    private final StudentsUseCase studentsUseCase;

    public StudentsController(StudentsUseCase studentsUseCase) {
        this.studentsUseCase = studentsUseCase;
    }

    @Override
    public ResponseEntity<StudentResponse> addStudent(String cpf, StudentRequest studentRequest, String apiKey) {
        log.info("Add Studing");

        Student student1 =  StudentMapper.INSTANCE.map(studentRequest);
        log.info(student1.toString());

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> deleteStudentByCpf(Long cpf, String apiKey) {
        log.info("Delete Studing ByCpf");
        return StudentsApi.super.deleteStudentByCpf(cpf, apiKey);
    }

    @Override
    public ResponseEntity<Void> deleteStudentById(Long id, String apiKey) {
        log.info("Delete Studing ById");
        return StudentsApi.super.deleteStudentById(id, apiKey);
    }

    @Override
    public ResponseEntity<List<StudentResponse>> findAll(@RequestHeader("api_key") String apiKey) {
        log.info("Find All");

        List<StudentResponse> studentsList = new ArrayList<StudentResponse>();
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.id("1")
                        .name("1")
                        .cpf("1")
                        .dateBirth("1")
                        .gender(StudentResponse.GenderEnum.MALE);
        studentsList.add(studentResponse);

        return ResponseEntity.ok(studentsList);


    }

    @Override
    public ResponseEntity<StudentResponse> findStudentByCpf(@RequestHeader("authorization") String authorization, String cpf) {
        log.info("Find Student By Cpf");
        return StudentsApi.super.findStudentByCpf(authorization, cpf);
    }

    @Override
    public ResponseEntity<StudentResponse> findStudentById(String id, String apiKey) {
        log.info("Find Student By Id");
        return StudentsApi.super.findStudentById(id, apiKey);
    }

    @Override
    public ResponseEntity<StudentResponse> updateStudent(String cpf, String id, StudentRequest studentRequest, String apiKey) {
        log.info("Find Student By cpf and Id");
        return StudentsApi.super.updateStudent(cpf, id, studentRequest, apiKey);
    }







}


//Todo:
// 1- End Rest
// 2 - Make a MockMvc Test
// 3 - Implements the Core - useCases,entites,repositories
// 4 - Implements Data Source - Implements Crud Data Source
// 5 - Pay attention about idempotency