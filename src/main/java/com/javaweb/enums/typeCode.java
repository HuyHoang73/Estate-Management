package com.javaweb.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum typeCode {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String typeCodeName;

    typeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }

    public static Map<String, String> getTypeCode() {
        Map<String,String> listTypeCode = new HashMap<String, String>();
        for(typeCode item : typeCode.values()){
            listTypeCode.put(item.toString(), item.typeCodeName);
        }
        return listTypeCode;
    }
}
