package com.baeldung.spring.data.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {
    @Id
    private String id;
    @Indexed
    private String name;
    @Reference
    private StudentClass stdClass;

    public Student()
    {
    }

    public Student(final String id, final String name, final StudentClass stdClass)
    {
        this.id = id;
        this.name = name;
        this.stdClass = stdClass;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public StudentClass getStdClass()
    {
        return stdClass;
    }

    public void setStdClass(final StudentClass stdClass)
    {
        this.stdClass = stdClass;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}