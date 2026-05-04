package com.bridgelabz.quantitymeasurement.model;

import java.time.LocalDateTime;

// UC16: Model for Database Record
public class QuantityRecord {
    private Long id;
    private String operationType;
    private Double value1;
    private String unit1;
    private Double value2;
    private String unit2;
    private String targetUnit;
    private Double result;
    private LocalDateTime createdAt;

    public QuantityRecord() {}

    public QuantityRecord(String operationType, Double value1, String unit1, Double value2, String unit2, String targetUnit, Double result) {
        this.operationType = operationType;
        this.value1 = value1;
        this.unit1 = unit1;
        this.value2 = value2;
        this.unit2 = unit2;
        this.targetUnit = targetUnit;
        this.result = result;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public Double getValue1() { return value1; }
    public void setValue1(Double value1) { this.value1 = value1; }

    public String getUnit1() { return unit1; }
    public void setUnit1(String unit1) { this.unit1 = unit1; }

    public Double getValue2() { return value2; }
    public void setValue2(Double value2) { this.value2 = value2; }

    public String getUnit2() { return unit2; }
    public void setUnit2(String unit2) { this.unit2 = unit2; }

    public String getTargetUnit() { return targetUnit; }
    public void setTargetUnit(String targetUnit) { this.targetUnit = targetUnit; }

    public Double getResult() { return result; }
    public void setResult(Double result) { this.result = result; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
