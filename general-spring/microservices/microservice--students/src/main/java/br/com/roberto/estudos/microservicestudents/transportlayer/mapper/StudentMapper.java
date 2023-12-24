package br.com.roberto.estudos.microservicestudents.transportlayer.mapper;

import br.com.roberto.estudos.microservicestudents.core.entities.Address;
import br.com.roberto.estudos.microservicestudents.core.entities.Student;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.AddressRequest;
import br.com.roberto.estudos.microservicestudents.transportlayer.dto.StudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "gender", target = "gender")
    Student map(final StudentRequest studentRequest);
    Address map(final AddressRequest addressRequest);

}
