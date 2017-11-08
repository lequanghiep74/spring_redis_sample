package com.yofiv.example.redis.controller;

import com.yofiv.example.redis.dto.BusDTO;
import com.yofiv.example.redis.entity.Bus;
import com.yofiv.example.redis.repo.BusCustomRepository;
import com.yofiv.example.redis.repo.BusRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/bus")
public class BusController
{
    @Inject
    BusRepository busRepository;

    @Inject
    BusCustomRepository busCustomRepository;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody BusDTO busDTO)
    {
        Bus bus = new Bus();
        bus.setLocation(busDTO.getLocation());
        bus.setNumPassenger(busDTO.getNumPassenger());
        bus.setPlateNo(busDTO.getPlateNo());
        bus.setCreatedDate(new Date());
        busRepository.save(bus);
        busCustomRepository.addBusToSearchByCreatedDate(bus.getId(), bus.getCreatedDate().getTime());

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam String id)
    {
        busRepository.delete(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/fetch",
            method = RequestMethod.GET)
    public List<Bus> fetch()
    {
        return busRepository.findAll();
    }

    @RequestMapping(value = "/fetchByPeriodTime",
            method = RequestMethod.GET)
    public List<Bus> fetch(@RequestParam Long fromTime,
                           @RequestParam Long toTime)
    {
        return busCustomRepository.findBusCreatedDateBetween(fromTime, toTime);
    }

    @RequestMapping(value = "/fetchBusNearLocation",
            method = RequestMethod.GET)
    public List<Bus> fetchBusNearLocation(@RequestParam String longitude,
                                          @RequestParam String latitude,
                                          @RequestParam double distance)
    {
        return busRepository.findByLocationNear(new Point(Double.valueOf(longitude), Double.valueOf(latitude))
                , new Distance(distance, Metrics.KILOMETERS));
    }
}
