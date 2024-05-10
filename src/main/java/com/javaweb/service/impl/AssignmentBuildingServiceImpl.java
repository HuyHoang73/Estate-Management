package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateAssignedBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteByBuildingAssign(buildingEntity);
        List<Long> listStaffId = assignmentBuildingDTO.getStaffs();
        for(int i = 0; i < listStaffId.size(); i++){
            UserEntity userEntity = userRepository.findById(listStaffId.get(i)).get();
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuildingAssign(buildingEntity);
            assignmentBuildingEntity.setStaffs(userEntity);
            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
    }
}
