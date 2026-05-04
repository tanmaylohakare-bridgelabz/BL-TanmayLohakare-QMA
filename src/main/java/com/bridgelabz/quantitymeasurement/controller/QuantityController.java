package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;
import com.bridgelabz.quantitymeasurement.dto.ResponseDTO;
import com.bridgelabz.quantitymeasurement.service.IQuantityService;

// UC15: Controller Layer enforcing N-Tier Architecture (Plain Java)
public class QuantityController {

    private final IQuantityService quantityService;

    // Dependency Injection Pattern (Constructor Injection)
    public QuantityController(IQuantityService quantityService) {
        this.quantityService = quantityService;
    }

    public ResponseDTO convertQuantity(QuantityRequestDTO requestDTO) {
        double result = quantityService.convertQuantity(requestDTO);
        return new ResponseDTO("Converted Successfully", result);
    }

    public ResponseDTO compareQuantities(QuantityRequestDTO requestDTO) {
        double result = quantityService.compareQuantities(requestDTO);
        boolean isEqual = (result == 1.0);
        return new ResponseDTO("Compared Successfully", isEqual);
    }

    public ResponseDTO addQuantities(QuantityRequestDTO requestDTO) {
        double result = quantityService.addQuantities(requestDTO);
        return new ResponseDTO("Added Successfully", result);
    }

    public ResponseDTO subtractQuantities(QuantityRequestDTO requestDTO) {
        double result = quantityService.subtractQuantities(requestDTO);
        return new ResponseDTO("Subtracted Successfully", result);
    }
}
