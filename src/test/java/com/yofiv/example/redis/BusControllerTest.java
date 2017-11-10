package com.yofiv.example.redis;

import com.yofiv.example.redis.controller.BusController;
import com.yofiv.example.redis.entity.Bus;
import com.yofiv.example.redis.repo.BusCustomRepository;
import com.yofiv.example.redis.repo.BusRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
@WebAppConfiguration
public class BusControllerTest
{
    @Inject
    BusRepository busRepository;

    @Inject
    BusCustomRepository busCustomRepository;

    private MockMvc restAreaMockMvc;

    @PostConstruct
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        BusController busController = new BusController();
        ReflectionTestUtils.setField(busController, "busRepository", busRepository);
        ReflectionTestUtils.setField(busController, "busCustomRepository", busCustomRepository);

        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();

        this.restAreaMockMvc = MockMvcBuilders.standaloneSetup(busController)
                .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver()).build();
    }

    @After
    public void after()
    {
        busRepository.deleteAll();
    }

    @Test
    public void saveBus()
            throws Exception
    {
        Bus bus = new Bus();
        bus.setPlateNo("A");
        bus.setLocation(new Point(1, 1));
        bus.setNumPassenger(10);
        bus.setCreatedDate(new Date());
        busRepository.save(bus);

        restAreaMockMvc.perform(get("/api/bus/fetch"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void fetchBus()
            throws Exception
    {
        for (int i = 0; i < 10000; i++) {
            Bus bus = new Bus();
            bus.setPlateNo("A");
            bus.setLocation(new Point(1, 1));
            bus.setNumPassenger(10);
            bus.setCreatedDate(new Date());
            busRepository.save(bus);
        }

        restAreaMockMvc.perform(get("/api/bus/fetch"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(10000)));
    }

    @Test
    public void fetchBusByPeriodTime()
            throws Exception
    {
        for (int i = 0; i < 100; i++) {
            Bus bus = new Bus();
            bus.setPlateNo("A");
            bus.setLocation(new Point(1, 1));
            bus.setNumPassenger(10);
            bus.setCreatedDate(new Date(1510304317070l));
            busRepository.save(bus);
            busCustomRepository.addBusToSearchByCreatedDate(bus.getId(), 1510304317070l);
        }
        Bus bus = new Bus();
        bus.setPlateNo("A");
        bus.setLocation(new Point(1, 1));
        bus.setNumPassenger(10);
        bus.setCreatedDate(new Date());
        busRepository.save(bus);
        busCustomRepository.addBusToSearchByCreatedDate(bus.getId(), bus.getCreatedDate().getTime());

        restAreaMockMvc.perform(get("/api/bus/fetchByPeriodTime")
                                        .param("fromTime", "0")
                                        .param("toTime", "1510304317070"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(100)));
    }
}
