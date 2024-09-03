package com.RESTAPI.demo.controlloer;

import com.RESTAPI.demo.dto.*;
import com.RESTAPI.demo.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalculationController {

    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }


    @PostMapping("/calculation")
    @ResponseBody
    public Object calculate(@RequestBody CalculationDto calculationDto) {

        CalculationResponseDto result = calculationService.calculate(calculationDto);

        if (result.isCorrect()) {
            return CalculationCorrectResponseDto.builder().isCorrect(true).build();
        }
        else {
            return result;
        }
    }

    @GetMapping("/calculation")
    @ResponseBody
    public List<CalculationHistoryResponseDto> searchHistory(@RequestBody CalculationSelectDto calculationSelectDto) {
        return calculationService.showHistory(calculationSelectDto.getUsername());
    }


}
