package com.bridgelabz.qmaservice.service;

import com.bridgelabz.qmaservice.dto.QuantityRequestDTO;

public interface IQuantityService {
    double convertQuantity(QuantityRequestDTO requestDTO);
    double compareQuantities(QuantityRequestDTO requestDTO);
    double addQuantities(QuantityRequestDTO requestDTO);
    double subtractQuantities(QuantityRequestDTO requestDTO);
}
