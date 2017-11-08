package com.yofiv.example.redis.repo;

import com.yofiv.example.redis.entity.Bus;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository
        extends JpaRepository<Bus, String>
{
    List<Bus> findByLocationNear(Point point, Distance distance);
}
