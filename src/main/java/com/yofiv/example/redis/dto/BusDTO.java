package com.yofiv.example.redis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

@JsonInclude
public class BusDTO
{
    private String id;
    private Point location;

    @JsonProperty("plate_no")
    private String plateNo;

    @JsonProperty("num_passenger")
    private int numPassenger;

    public BusDTO()
    {
    }

    public BusDTO(final String id, final String plateNo, final Point location, final int numPassenger)
    {
        this.id = id;
        this.plateNo = plateNo;
        this.location = location;
        this.numPassenger = numPassenger;
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
}
