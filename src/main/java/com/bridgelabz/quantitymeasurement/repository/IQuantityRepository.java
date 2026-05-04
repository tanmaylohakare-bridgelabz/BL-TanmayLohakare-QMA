package com.bridgelabz.quantitymeasurement.repository;

import com.bridgelabz.quantitymeasurement.model.QuantityRecord;

// UC16: Separation of Concerns in Persistence
public interface IQuantityRepository {
    void initializeSchema();
    void save(QuantityRecord record);
}
