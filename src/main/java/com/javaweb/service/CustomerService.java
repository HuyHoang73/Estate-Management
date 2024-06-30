package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> findAll(CustomerSearchRequest request, Pageable pageable);
    void createOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerEntity findById(Long id);
    void deleteCustomer(List<Long> ids);
    ResponseDTO getStaff(Long id);
    int countTotalItems();
    void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
}
