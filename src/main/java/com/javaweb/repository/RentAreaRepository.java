package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository  extends JpaRepository<RentAreaEntity, Long>{
    void deleteAllByBuildingRentArea_Id(Long buildingId);
    void deleteByBuildingRentArea_IdIn(List<Long> buildingId);
}
