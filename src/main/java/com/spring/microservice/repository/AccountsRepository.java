package com.spring.microservice.repository;

import com.spring.microservice.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByAccountNumber(Long accountNumber);

//    @Query("SELECT a FROM Accounts a WHERE a.taxCode IN(10,20,30) :taxCodes AND a.createdDate BETWEEN :fromDate AND :toDate")
//    List<Accounts> findByTaxCodeInAndCreatedDateBetween(List<Integer> taxCodes, Date fromDate, Date toDate);

    List<Accounts> findByTaxCodeInAndCreatedDateBetween(List<String> taxCodes, Date fromDate, Date toDate);

}