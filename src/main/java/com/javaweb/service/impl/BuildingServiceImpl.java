package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest request) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(request);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = buildingConverter.toBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }

    @Override
    public BuildingEntity findById(Long id) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(id);
        if (buildingEntity.isPresent()) {
            return buildingEntity.get();
        } else {
            throw new EntityNotFoundException("Building not found with id: " + id);
        }
    }

    @Override
    @Transactional
    public void createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.fromDTOtoEntityWithoutRentArea(buildingDTO);
        buildingRepository.save(buildingEntity);
        String[] convertString = buildingDTO.getRentArea().split(",");
        if(convertString.length > 0) {
            rentAreaRepository.deleteByBuildingRentArea_Id(buildingEntity.getId());
            for(String str : convertString){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Long.parseLong(str.trim()));
                rentAreaEntity.setBuildingRentArea(buildingEntity);
                rentAreaRepository.save(rentAreaEntity);
            }
        }
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
        Long[] idList = ids.toArray(new Long[ids.size()]);
        for(Long item : idList) {
            rentAreaRepository.deleteByBuildingRentArea_Id(item);
        }
        buildingRepository.deleteByIdIn(idList);
    }
}
