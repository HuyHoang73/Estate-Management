package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String addOrUpdateCustomer(@RequestBody CustomerDTO CustomerDTO){
        customerService.createOrUpdateCustomer(CustomerDTO);
        return new String("Thành công");
    }

    @DeleteMapping("/{ids}")
    public String deleteCustomer(@PathVariable List<Long> ids){
        customerService.deleteCustomer(ids);
        return new String("Thành công");
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable ("id") Long id){
        return customerService.getStaff(id);
    }

    @PutMapping
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.assignCustomer(assignmentCustomerDTO);
    }

    @PostMapping("/transaction")
    public String addOrUpdaateTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.createOrUpdateTransaction(transactionDTO);
        return new String("Thành công");
    }
}
