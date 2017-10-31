package com.baeldung.spring.data.redis.repo;

import com.baeldung.spring.data.redis.model.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository
        extends CrudRepository<StudentClass, String>
{
    StudentClass findOneByName(String name);
}
