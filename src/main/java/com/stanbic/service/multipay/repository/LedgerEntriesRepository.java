package com.stanbic.service.multipay.repository;

import com.stanbic.service.multipay.entity.LedgerEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LedgerEntriesRepository extends CrudRepository<LedgerEntries, Long> {
}
