package com.baeldung.spring.data.redis.controller;

import com.baeldung.spring.data.redis.model.StudentClass;
import com.baeldung.spring.data.redis.repo.ClassRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class ClassController
{
    @Inject
    ClassRepository classRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createClass(@RequestParam("id") String id,
                                @RequestParam("name") String name)
    {
        StudentClass studentClass = new StudentClass();
        studentClass.setId(id);
        studentClass.setName(name);

        classRepository.save(studentClass);
        return "ok";
    }

    @RequestMapping("/fetch")
    public List<StudentClass> fetchAllClass()
    {
        return (List<StudentClass>) classRepository.findAll();
    }

    @RequestMapping("/fetch/{name}")
    public StudentClass findOneByName(@PathVariable String name)
    {
        return classRepository.findOneByName(name);
    }
}
