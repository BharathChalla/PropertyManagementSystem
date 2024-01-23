package com.mango.propertymanagement.controller;

import com.mango.propertymanagement.dto.CalculatorDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2) {
        if (num1 == null || num2 == null) return null;
        return num1 + num2;
    }

    @GetMapping("/sub/{num1}/{num2}")
    // public Double subtract(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2) {
    public Double subtract(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2) {
        return (num1 > num2) ? (num1 - num2) : (num2 - num1);
    }

    @PostMapping("/mul")
    public Double multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        if (calculatorDTO != null) {
            result = calculatorDTO.getNum1() * calculatorDTO.getNum2()
                    * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        }
        return result;
    }
}
