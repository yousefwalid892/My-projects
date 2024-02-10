
package com.example.CrudOperation.mapper;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);

    List<StudentDto> toDto(List<Student> students);

    Student toUpdate (StudentDto studentDto,@MappingTarget Student student);

    Student toEntity(StudentDto studentDto);
}
