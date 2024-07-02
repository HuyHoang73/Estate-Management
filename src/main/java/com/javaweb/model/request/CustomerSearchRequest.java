package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchRequest extends AbstractDTO {
    private String fullname;
    private Long staffId;
    private String phone;
    private String email;
    private String status;
}
