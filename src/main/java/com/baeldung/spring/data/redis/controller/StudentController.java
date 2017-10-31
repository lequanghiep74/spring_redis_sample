package com.baeldung.spring.data.redis.controller;

import com.baeldung.spring.data.redis.model.Student;
import com.baeldung.spring.data.redis.repo.ClassRepository;
import com.baeldung.spring.data.redis.repo.StudentRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController
{
    @Inject
    StudentRepository studentRepository;

    @Inject
    ClassRepository classRepository;

    @RequestMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createStudent(@RequestParam("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("classId") String classId)
    {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
//        student.setStdClass(classRepository.findById(classId).get());

        studentRepository.save(student);
        return "ok";
    }

    @RequestMapping("/fetch")
    public List<Student> fetchAllStudent()
    {
        return (List<Student>) studentRepository.findAll();
    }

    @RequestMapping("/fetch/{name}")
    public Student findOneByName(@PathVariable String name)
    {
        return studentRepository.findOneByName(name);
    }
}
