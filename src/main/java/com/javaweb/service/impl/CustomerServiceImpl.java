package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest request, Pageable pageable) {
        List<CustomerEntity> listCustomer = customerRepository.findAll(request, pageable);
        List<CustomerSearchResponse> result = new ArrayList<>();
        listCustomer.forEach(customerEntity -> {
            CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity, CustomerSearchResponse.class);
            result.add(customerSearchResponse);
        });
        return result;
    }

    @Override
    @Transactional
    public void createOrUpdateCustomer(CustomerDTO customerDTO) {
        Long customerId = customerDTO.getId();
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setIsActive(1L);
        if (customerId != null) { // update
            CustomerEntity foundCustomer= customerRepository.findById(customerId)
                    .orElseThrow(() -> new NotFoundException("Customer not found!"));
            customerEntity.setCreatedBy(foundCustomer.getCreatedBy());
            customerEntity.setCreatedDate(foundCustomer.getCreatedDate());
            customerEntity.setStaffsAssign(customerRepository.findById(customerId).get().getStaffsAssign());
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity findById(Long id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (customerEntity.isPresent()) {
            return customerEntity.get();
        } else {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteCustomer(List<Long> ids) {
        for (Long item : ids) {
            CustomerEntity customerEntity = customerRepository.findById(item).get();
            customerEntity.setIsActive(0L);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public ResponseDTO getStaff(Long id) {
        Map<Long, String> listStaffs = userService.getStaffs();
        CustomerEntity customerEntity = customerService.findById(id);
        Map<Long, String> listStaffAssigned = userService.getStaffsAssignedCustomer(customerEntity);
        List<StaffResponseDTO> staffAssignment = userService.getStaffResponseDTOS(listStaffs, listStaffAssigned);
        ResponseDTO result = new ResponseDTO();
        result.setData(staffAssignment);
        result.setMessage("Success");
        return result;
    }

    @Override
    public int countTotalItems() {
        return customerRepository.countTotalItem();
    }

    @Override
    @Transactional
    public void assignCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> listStaffs = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setStaffsAssign(listStaffs);
        customerRepository.save(customerEntity);
    }
}
