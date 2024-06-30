package com.javaweb.controller.admin;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs", userService.getStaffs());
        mav.addObject("districtCode", DistrictCode.district());
        mav.addObject("typeCode", TypeCode.getTypeCode());
        DisplayTagUtils.of(request, buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_MANAGER")){
            result = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        } else {
            Long id = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(id);
            result = buildingService.findAll(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()));
        }
        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        buildingSearchResponse.setListResult(result);
        buildingSearchResponse.setTotalItems(buildingService.countTotalItems());
        mav.addObject("buildingList", buildingSearchResponse);
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
        mav.addObject("districtCode", DistrictCode.district());
        mav.addObject("typeCode", TypeCode.getTypeCode());
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
        mav.addObject("districtCode", DistrictCode.district());
        mav.addObject("typeCode", TypeCode.getTypeCode());
        BuildingEntity buildingEntity = buildingService.findById(id);
        BuildingDTO buildingDTO = buildingConverter.fromEntityToDTO(buildingEntity);
        mav.addObject("buildingEdit", buildingDTO);
        return mav;
    }
}
