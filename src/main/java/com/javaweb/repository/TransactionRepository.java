package com.javaweb.repository;

import com.javaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByCustomerTransactions_IdAndCode(Long customerId,String code);
    List<TransactionEntity> findAllByCustomerTransactions_Id(Long customerId);
}
