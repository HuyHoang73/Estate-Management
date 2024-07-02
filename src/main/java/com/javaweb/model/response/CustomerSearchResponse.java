package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchResponse extends AbstractDTO {
    private String fullname;
    private String phone;
    private String email;
    private String demand;
    private String status;
}
