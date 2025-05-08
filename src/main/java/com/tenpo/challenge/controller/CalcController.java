package com.tenpo.challenge.controller;

import com.tenpo.challenge.clases.CalcRequest;
import com.tenpo.challenge.service.CalcService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/calculate")
public class CalcController {
    private final CalcService service;

    public CalcController(CalcService service) {
        this.service = service;
    }

    @PostMapping
    public Double returnPercentage(@RequestBody CalcRequest request){
        return this.service.calcFinalNumber(request);
    }
}
