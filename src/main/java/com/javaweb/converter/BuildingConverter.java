package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Phương thức này để chuyển đổi từ BuildingEntity sang BuildingSearchResponse
     * @param item - dưới dạng Entity
     * @return item - dưới dạng DTO
     */
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrict());
        String rentAreas = item.getRentAreaEntities().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(", "));
        building.setRentArea(rentAreas);
        return building;
    }

    /**
     * Phương thức này để chuyển đổi từ BuildingEntity sang BuildingDTO
     * @param buildingEntity - dưới dạng Entity
     * @return dưới dạng DTO
     */
    public BuildingDTO fromEntityToDTO(BuildingEntity buildingEntity) {
        BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
        String rentAreas = buildingEntity.getRentAreaEntities().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(", "));
        building.setRentArea(rentAreas);
        String[] typeCode = buildingEntity.getType().split(",");
        List<String> typeCodes = new ArrayList<>(Arrays.asList(typeCode));
        building.setTypeCode(typeCodes);
        return building;
    }

    /**
     * Phương thức này để chuyển đổi từ BuildingDTO sang BuildingENtity
     * @param buildingDTO - dưới dạng DTO
     * @return dưới dạng Entity
     */
    public BuildingEntity fromDTOtoEntityWithoutRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity =  modelMapper.map(buildingDTO, BuildingEntity.class);
        StringBuilder typeCode = new StringBuilder();
        for(String item : buildingDTO.getTypeCode()){
            if(typeCode.length() > 0){
                typeCode.append(",");
            }
            typeCode.append(item);
        }
        buildingEntity.setType(typeCode.toString());
        return buildingEntity;
    }
}
