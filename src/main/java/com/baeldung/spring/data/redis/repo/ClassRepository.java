package com.baeldung.spring.data.redis.repo;

import com.baeldung.spring.data.redis.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository
        extends JpaRepository<StudentClass, String>
{
    StudentClass findOneByName(String name);
}
