package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignmentBuildingDTO {
    private Long buildingId;
    private List<Long> staffs;
}
