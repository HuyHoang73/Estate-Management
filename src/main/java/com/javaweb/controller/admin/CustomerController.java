package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.UserConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.RoleService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest modelSearch, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("staffsCustomer", userService.getStaffs());
        mav.addObject("statusTransaction", Status.type());
        DisplayTagUtils.of(request, modelSearch);
        List<CustomerSearchResponse> result = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_MANAGER")){
            result = customerService.findAll(modelSearch, PageRequest.of(modelSearch.getPage() - 1, modelSearch.getMaxPageItems()));
        } else {
            Long id = SecurityUtils.getPrincipal().getId();
            modelSearch.setStaffId(id);
            result = customerService.findAll(modelSearch, PageRequest.of(modelSearch.getPage() - 1, modelSearch.getMaxPageItems()));
        }
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setListResult(result);
        customerSearchResponse.setTotalItems(customerService.countTotalItems());
        mav.addObject("customers", customerSearchResponse);

        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView buildingEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusTransaction", Status.type());
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusTransaction", Status.type());
        mav.addObject("typeTransaction", TransactionType.transactionType());
        CustomerEntity customerEntity = customerService.findById(id);
        List<UserEntity> staffsAsign = customerEntity.getStaffsAssign();

        boolean flag = false;
        for (UserEntity userEntity : staffsAsign) {
            if (userEntity.getId() == SecurityUtils.getPrincipal().getId()) {
                flag = true;
                break;
            }
        }

        if(flag || SecurityUtils.getAuthorities().contains("ROLE_MANAGER")){
            CustomerDTO cutomerDTO = modelMapper.map(customerEntity, CustomerDTO.class);

            TransactionResponse cskhTransaction = new TransactionResponse();
            cskhTransaction.setListResult(transactionService.findAllByCustomerIdAndCode(id, "CSKH"));
            TransactionResponse ddxTransaction = new TransactionResponse();
            ddxTransaction.setListResult(transactionService.findAllByCustomerIdAndCode(id, "DDX"));

            mav.addObject("customerEdit", cutomerDTO);
            mav.addObject("cskhTransactions", cskhTransaction);
            mav.addObject("ddxTransactions", ddxTransaction);
        } else {

            throw new AccessDeniedException("Bạn không có quyền truy cập thông tin khách hàng này!");

        }
        return mav;
    }
}
