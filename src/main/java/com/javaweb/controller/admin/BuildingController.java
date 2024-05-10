package com.javaweb.controller.admin;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingConverter buildingConverter;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCode", typeCode.getTypeCode());
        List<BuildingSearchResponse> result = buildingService.findAll(buildingSearchRequest);
        mav.addObject("buildingList", result);
        return mav;
    }

    /**
     * Hàm điều hướng sang thêm tòa nhà
     * @param buildingDTO
     * @return
     */
    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCode", typeCode.getTypeCode());
        return mav;
    }

    /**
     * Hàm điều hướng sang sửa tòa nhà và lấy dữ liệu cũ của tòa nhà cần sửa
     * @param id - id tòa nhà cần sửa
     * @return
     */
    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCode", typeCode.getTypeCode());
        BuildingEntity buildingEntity = buildingService.findById(id);
        BuildingDTO buildingDTO = buildingConverter.fromEntityToDTO(buildingEntity);
        mav.addObject("buildingEdit", buildingDTO);
        return mav;
    }
}