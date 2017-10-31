package com.baeldung.spring.data.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@RedisHash("Class")
public class StudentClass
        implements Serializable
{
    @Id
    private String id;
    private String name;

    public StudentClass()
    {
    }

    public StudentClass(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}