package com.stanbic.service.multipay.repository;

import com.stanbic.service.multipay.entity.Ledger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends CrudRepository<Ledger, Long> {
}
