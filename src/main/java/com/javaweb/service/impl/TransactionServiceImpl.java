package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<TransactionResponse> findAllByCustomerIdAndCode(Long customerId, String code) {
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByCustomerTransactions_IdAndCode(customerId, code);
        return transactionConverter.fromEntityToResponse(transactionEntities);
    }

    @Override
    public void createOrUpdateTransaction(TransactionDTO transactionDTO) {
        CustomerEntity customerEntity = customerService.findById(transactionDTO.getCustomerId());
        TransactionEntity transactionEntity = transactionConverter.fromDTOtoEntity(transactionDTO);
        transactionEntity.setCustomerTransactions(customerEntity);
        transactionRepository.save(transactionEntity);
    }
}
