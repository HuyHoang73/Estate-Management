package com.javaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends AbstractDTO{
    private String fullname;
    private Long staffId;
    private String phone;
    private String email;
    private String demand;
    private String status;
    private String companyName;
}
