package com.javaweb.service;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> findAllByCustomerIdAndCode(Long customerId, String code);
    void createOrUpdateTransaction(TransactionDTO transactionDTO);
}
