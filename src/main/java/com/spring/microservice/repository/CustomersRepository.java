package com.spring.microservice.repository;

import com.spring.microservice.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

    @Query("SELECT tcc FROM Customers tcc JOIN Organization tpo ON tcc.partyNbr = tpo.partyNbr WHERE tcc.createdDate BETWEEN :fromDate AND :toDate AND tcc.casStatusId LIKE '2'")
    List<Customers> countActivatedCompanies(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}