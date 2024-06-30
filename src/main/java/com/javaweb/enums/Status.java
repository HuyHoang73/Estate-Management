package com.javaweb.enums;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum Status {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public static Map<String, String> type(){
        Map<String, String> listType = new LinkedHashMap<>();
        for(Status type : Status.values()){
            listType.put(type.getStatusName(), type.getStatusName());
        }
        return listType;
    }
}
