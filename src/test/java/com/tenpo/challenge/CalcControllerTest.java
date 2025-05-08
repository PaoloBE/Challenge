package com.tenpo.challenge;

import com.tenpo.challenge.clases.CalcRequest;
import com.tenpo.challenge.clases.ExternalResponse;
import com.tenpo.challenge.client.ExternalClient;
import com.tenpo.challenge.controller.CalcController;
import com.tenpo.challenge.entity.LogMetric;
import com.tenpo.challenge.repository.LogMetricRepository;
import com.tenpo.challenge.service.CalcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ChallengeApplication.class, properties = {"server.port=0",
        "spring.datasource.url=jdbc:h2:mem:sys_gest_pv_1;DB_CLOSE_ON_EXIT=FALSE","local.server.port=9090"})
@TestPropertySource(locations = "classpath:application-test.properties")
public class CalcControllerTest {

    private CalcController controller;

    @MockitoBean
    private CalcService service;

    @MockitoBean
    private LogMetricRepository repository;

    @MockitoBean
    private ExternalClient client;

    @MockitoBean
    private RestClient restClient;

    @BeforeEach
    void setup() {
        service = new CalcService(repository, client);
        controller = new CalcController(this.service);
    }

    @Test
    void returnCalcOk() {
        when(this.repository.save(any())).thenReturn(new LogMetric());
        when(this.client.callNumber()).thenReturn(new ExternalResponse(12));
        Double response = this.controller.returnPercentage(this.requestGen());
        Assertions.assertTrue(response > 0.0);
    }

    @Test
    void returnCalcZero() {
        when(this.repository.save(any())).thenReturn(new LogMetric());
        when(this.client.callNumber()).thenReturn(new ExternalResponse(0));
        Double response = this.controller.returnPercentage(this.requestGen());
        Assertions.assertTrue(response == 0.0);
    }

    private CalcRequest requestGen() {
        return new CalcRequest(12,4);
    }
}
