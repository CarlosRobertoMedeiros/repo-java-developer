package br.com.roberto.estudos.microservicestudents.transportlayer.mapper;

import br.com.roberto.estudos.microservicestudents.core.entities.Student;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.AddressRequest;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.StudentRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class StudentMapperTest {

    @Test
    void shouldMapStudentRequestToStudent() {
        //given
        StudentRequest studentRequest = new StudentRequest();
        List<AddressRequest> addressList = Arrays.asList(new AddressRequest()
                .street("437 Lytton")
                .city("Palo Alto")
                .brasilState(AddressRequest.BrasilStateEnum.AC)
                .zip("72000")
                .state(AddressRequest.StateEnum.A));

        studentRequest
                .name("Carlos Roberto")
                .dateBirth("1981-12-12")
                .mothersName("Raimunda Medeiros de Lima")
                .fathersName("José Carlos de Lima")
                .gender(StudentRequest.GenderEnum.MALE)
                .addressList(addressList);


        Student student = StudentMapper.INSTANCE.map(studentRequest);

        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getName()).contains("Carlos Roberto");
        Assertions.assertThat(student.getFathersName()).contains("José");
        Assertions.assertThat(student.getMothersName()).contains("Raimunda");

    }

}