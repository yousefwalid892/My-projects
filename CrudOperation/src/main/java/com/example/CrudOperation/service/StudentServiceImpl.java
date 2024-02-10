package com.example.CrudOperation.service;

import com.example.CrudOperation.exception.StudentAlreadyExistsException;
import com.example.CrudOperation.exception.StudentNotFoundException;
import com.example.CrudOperation.mapper.StudentMapper;
import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.repository.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepo studentRepo;
    @Autowired
    private StudentMapper studentMapper;

    Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
   @Cacheable(value = "addStudent" ,key = "#studentDto.id")
    @Override
    public Student createStudent(StudentDto studentDto) {
        Optional<Student> stu = studentRepo.findById(studentDto.getId());
        if (stu.isEmpty()) {
            log.info("This is log",studentDto.getId(),studentDto.getFirstName());
            return studentRepo.save(studentMapper.toEntity(studentDto));
        } else {
            log.error("Student is existing");
            throw new StudentAlreadyExistsException("Student exist");
        }
    }
    @Cacheable(value = "returnAllStudents")
    @Override
    public List<StudentDto> getAllStudents() {
           List<StudentDto> dtos = studentMapper.toDto(studentRepo.findAll());
           return dtos;
    }

    @Cacheable(value = "getStudentById", key = "#id")
    @Override
    public StudentDto getStudent(int id) {
        Optional<Student> stu = studentRepo.findById(id);
        if(stu.isPresent()){
            return studentMapper.toDto(stu.get());
        }
        else{
            log.error("Student not exist");
            throw new StudentNotFoundException("Student not exist with no."+ id);
        }
    }
  //  @CachePut(value = "updateStudentById", key = "#studentDto.id")
    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
          Student student = studentRepo.findById(studentDto.getId()).get();        //return id of student(dto) from DB.
          Student mapped = studentMapper.toUpdate(studentDto,student);      //put the updated properties of dto into entity.
          return studentMapper.toDto(studentRepo.save(mapped));             //then convert to dto and return dto.
    }
    @CacheEvict(value = "deleteStudentById", key = "#id")
    @Override
    public void deleteStudent(int id) {
           studentRepo.deleteById(id);
    }
}


