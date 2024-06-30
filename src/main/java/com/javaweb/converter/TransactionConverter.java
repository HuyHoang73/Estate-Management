package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    public TransactionEntity fromDTOtoEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity =  modelMapper.map(transactionDTO, TransactionEntity.class);
        CustomerEntity customerEntity = customerService.findById(transactionDTO.getCustomerId());
        transactionEntity.setCustomerTransactions(customerEntity);
        return transactionEntity;
    }

    public List<TransactionResponse> fromEntityToResponse(List<TransactionEntity> transactionEntities) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        transactionEntities.forEach(item -> {
            TransactionResponse transactionResponse = modelMapper.map(item, TransactionResponse.class);
            transactionResponses.add(transactionResponse);
        });
        return transactionResponses;
    }
}
