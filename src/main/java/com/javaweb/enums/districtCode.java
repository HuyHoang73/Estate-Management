package com.javaweb.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum districtCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_TB("Quận Tân Bình"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11");

    private final String districtName;

    districtCode(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> district(){
        Map<String, String> listDistrict = new HashMap<>();
        for(districtCode item : districtCode.values()){
            listDistrict.put(item.toString(), item.getDistrictName());
        }
        return listDistrict;
    }
}
