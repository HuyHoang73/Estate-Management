package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
//import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.*;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

//    @Autowired
//    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest request, Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(request, pageable);
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
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.fromDTOtoEntityWithoutRentArea(buildingDTO);
        if (buildingId != null) { // update
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                    .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setAvatar(foundBuilding.getAvatar());
        }
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
        List<BuildingEntity> listBuilding = buildingRepository.findAllByIdIn(ids);
        listBuilding.forEach(itemBuilding -> {
            itemBuilding.getStaffs().forEach(userItem -> {
                userItem.getBuildingAssign().remove(itemBuilding);
            });
            itemBuilding.getStaffs().clear();
        });
        buildingRepository.deleteAllByIdIn(ids);
    }

    @Override
    public ResponseDTO getStaff(Long id) {
        Map<Long, String> listStaffs = userService.getStaffs();
        BuildingEntity buildingEntity = buildingService.findById(id);
        Map<Long, String> listStaffAssigned = userService.getStaffsAssigned(buildingEntity);
        List<StaffResponseDTO> staffAssignment = userService.getStaffResponseDTOS(listStaffs, listStaffAssigned);
        ResponseDTO result = new ResponseDTO();
        result.setData(staffAssignment);
        result.setMessage("Success");
        return result;
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItem();
    }

    @Override
    @Transactional
    public void assignBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> listStaffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setStaffs(listStaffs);
        buildingRepository.save(buildingEntity);
    }

    /**
     * Hàm này được dùng để xử lý việc cập nhật hoặc thêm hình ảnh
     * @param buildingDTO - đầu vào từ client
     * @param buildingEntity - tham chiếu đến CSDL
     */
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }
}
