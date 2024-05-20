package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest request, Pageable pageable);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    BuildingEntity findById(Long id);
    void deleteBuilding(List<Long> ids);
    ResponseDTO getStaff(Long id);
    int countTotalItems();
    void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
