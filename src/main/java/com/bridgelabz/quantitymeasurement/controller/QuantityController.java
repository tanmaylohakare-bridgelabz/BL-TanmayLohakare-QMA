package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;
import com.bridgelabz.quantitymeasurement.dto.ResponseDTO;
import com.bridgelabz.quantitymeasurement.service.IQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// UC15: Controller Layer exposing N-Tier REST Endpoints
@RestController
@RequestMapping("/api/quantity")
public class QuantityController {

    private final IQuantityService quantityService;

    @Autowired
    public QuantityController(IQuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PostMapping("/convert")
    public ResponseEntity<ResponseDTO> convertQuantity(@RequestBody QuantityRequestDTO requestDTO) {
        double result = quantityService.convertQuantity(requestDTO);
        return new ResponseEntity<>(new ResponseDTO("Converted Successfully", result), HttpStatus.OK);
    }

    @PostMapping("/compare")
    public ResponseEntity<ResponseDTO> compareQuantities(@RequestBody QuantityRequestDTO requestDTO) {
        double result = quantityService.compareQuantities(requestDTO);
        boolean isEqual = (result == 1.0);
        return new ResponseEntity<>(new ResponseDTO("Compared Successfully", isEqual), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addQuantities(@RequestBody QuantityRequestDTO requestDTO) {
        double result = quantityService.addQuantities(requestDTO);
        return new ResponseEntity<>(new ResponseDTO("Added Successfully", result), HttpStatus.OK);
    }

    @PostMapping("/subtract")
    public ResponseEntity<ResponseDTO> subtractQuantities(@RequestBody QuantityRequestDTO requestDTO) {
        double result = quantityService.subtractQuantities(requestDTO);
        return new ResponseEntity<>(new ResponseDTO("Subtracted Successfully", result), HttpStatus.OK);
    }
}
