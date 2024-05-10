package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest request);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    BuildingEntity findById(Long id);
    void deleteBuilding(List<Long> ids);
}
