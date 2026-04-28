package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;

// UC15: Service Interface (Dependency Inversion Principle)
public interface IQuantityService {
    double compareQuantities(QuantityRequestDTO requestDTO);
    double addQuantities(QuantityRequestDTO requestDTO);
    double subtractQuantities(QuantityRequestDTO requestDTO);
    double convertQuantity(QuantityRequestDTO requestDTO);
}
