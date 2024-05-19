package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.createOrUpdateBuilding(buildingDTO);
        return new String("Thành công");
    }

    @DeleteMapping("/{ids}")
    public String deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteBuilding(ids);
        return new String("Thành công");
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable ("id") Long id){
        return buildingService.getStaff(id);
    }

    @PutMapping
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.assignBuilding(assignmentBuildingDTO);
    }
}
