package com.yofiv.example.redis.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.util.Date;

@RedisHash("Bus")
public class Bus
        implements Serializable
{
    @Id
    private String id;
    private String plateNo;
    @GeoIndexed
    private Point location;
    private int numPassenger;
    @Indexed
    @CreatedDate
    private Date createdDate;

    public Bus()
    {
    }

    public Bus(final String id, final String plateNo, final Point location, final int numPassenger, final Date createdDate)
    {
        this.id = id;
        this.plateNo = plateNo;
        this.location = location;
        this.numPassenger = numPassenger;
        this.createdDate = createdDate;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getPlateNo()
    {
        return plateNo;
    }

    public void setPlateNo(final String plateNo)
    {
        this.plateNo = plateNo;
    }

    public Point getLocation()
    {
        return location;
    }

    public void setLocation(final Point location)
    {
        this.location = location;
    }

    public int getNumPassenger()
    {
        return numPassenger;
    }

    public void setNumPassenger(final int numPassenger)
    {
        this.numPassenger = numPassenger;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate)
    {
        this.createdDate = createdDate;
    }

}