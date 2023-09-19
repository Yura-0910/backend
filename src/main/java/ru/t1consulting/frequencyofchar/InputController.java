package ru.t1consulting.frequencyofchar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InputController {
    private CalculationService calculationService;

    @Autowired
    public InputController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PutMapping("/count-chars")
    public String getDataFromRequest(@RequestBody String dataFromRequest){
        return calculationService.initialCheckAllCharIsLetter(dataFromRequest);
    }
}














