package com.bridgelabz.qmaservice.repository;

import com.bridgelabz.qmaservice.model.QuantityRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuantityRepository extends JpaRepository<QuantityRecord, Long> {
}
