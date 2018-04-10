package com.sheensoft.persistance.repository;

import com.sheensoft.domain.Rate;
import com.sheensoft.domain.RateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, String> {
    @Query("Select r from Rate r WHERE r.rateId.currencyFrom = :currFrom AND r.rateId.currencyTo = :currTo")
    public List<Rate> findByIdNew(@Param("currFrom") String currFrom, @Param("currTo") String currTo);
}

