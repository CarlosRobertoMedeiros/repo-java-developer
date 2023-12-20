package br.com.roberto.estudos.microservicestudents.transportlayer.mapper;

import br.com.roberto.estudos.microservicestudents.core.entities.GenderEnum;
import br.com.roberto.estudos.microservicestudents.core.entities.Student;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.StudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    @Mapping(source ="name",target ="name")
    @Mapping(source ="dateBirth",target ="dateBirth")
    @Mapping(source ="mothersName",target ="mothersName")
    @Mapping(source ="fathersName",target ="fathersName")
    @Mapping(source ="gender",target ="gender")
    Student toStudent(final StudentRequest studentRequest);


}
