package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.exception.MyException;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    List<UserDTO> getUsers(String searchValue, Pageable pageable);
    int getTotalItems(String searchValue);
    UserDTO findOneByUserName(String userName);
    UserDTO findUserById(long id);
    UserDTO insert(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void updatePassword(long id, PasswordDTO userDTO) throws MyException;
    UserDTO resetPassword(long id);
    UserDTO updateProfileOfUser(String id, UserDTO userDTO);
    void delete(long[] ids);
//    ResponseDTO listStaff(Long buildingId);
    List<UserDTO> getAllUsers(Pageable pageable);
    int countTotalItems();
    Map<Long, String> getStaffs();
    Map<Long, String> getStaffsAssigned(BuildingEntity buildingEntity);
    List<StaffResponseDTO> getStaffResponseDTOS(Map<Long, String> listStaffs, Map<Long, String> listStaffAssigned);
}
