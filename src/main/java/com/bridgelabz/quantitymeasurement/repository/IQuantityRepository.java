package com.bridgelabz.quantitymeasurement.repository;

import com.bridgelabz.quantitymeasurement.model.QuantityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// UC17: Spring Data JPA Repository
@Repository
public interface IQuantityRepository extends JpaRepository<QuantityRecord, Long> {
}
