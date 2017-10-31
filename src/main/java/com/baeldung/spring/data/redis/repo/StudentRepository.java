package com.baeldung.spring.data.redis.repo;

import com.baeldung.spring.data.redis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, String>
{
    Student findOneByName(String name);
}
