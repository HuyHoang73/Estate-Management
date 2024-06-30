package com.javaweb.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private final String name;


    TransactionType(String name) {
        this.name = name;
    }

    public static Map<String, String> transactionType(){
        Map<String, String> typeCodes = new HashMap<String, String>();
        for(TransactionType type : TransactionType.values()){
            typeCodes.put(type.toString(), type.getName());
        }
        return typeCodes;
    }
}
