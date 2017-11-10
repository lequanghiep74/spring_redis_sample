package com.yofiv.example.redis.repo;

import com.yofiv.example.redis.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BusCustomRepository
{
    @Inject
    BusRepository busRepository;
    RedisTemplate<String, Object> redisTemplate;
    final String KEY = "Bus:createdDate";

    @Autowired
    public BusCustomRepository(final RedisTemplate<String, Object> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    public boolean addBusToSearchByCreatedDate(String id, long fromDate)
    {
        return redisTemplate.opsForZSet().add(KEY, id, fromDate);
    }

    public List<Bus> findBusCreatedDateBetween(long fromDate, long toDate)
    {
        List<Bus> buses = new ArrayList<>();
        Set<Object> ids = redisTemplate.opsForZSet().rangeByScore(KEY, fromDate, toDate);
        if (ids != null) {
            for (Object id : ids) {
                Bus bus = busRepository.findOne(id.toString());
                if (bus != null) {
                    buses.add(bus);
                }
            }
        }
        return buses;
    }
}
