package com.javaweb.builder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BuildingSearchBuilder {
    private String name;
    private String ward;
    private String district;
    private String street;
    private Long numberOfBasement;
    private List<String> typeCode = new ArrayList<>();
    private String managerName;
    private String managerPhone;
    private Long floorArea;
    private String direction;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String level;
    private Long staffID;

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.ward = builder.ward;
        this.district = builder.district;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.floorArea = builder.floorArea;
        this.direction = builder.direction;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.level = builder.level;
        this.staffID = builder.staffID;
    }

    public String getName() {
        return name;
    }
    public String getWard() {
        return ward;
    }
    public String getDistrict() {
        return district;
    }
    public String getStreet() {
        return street;
    }
    public Long getNumberOfBasement() {
        return numberOfBasement;
    }
    public List<String> getTypeCode() {
        return typeCode;
    }
    public String getManagerName() {
        return managerName;
    }
    public String getManagerPhone() {
        return managerPhone;
    }
    public Long getFloorArea() {
        return floorArea;
    }
    public String getDirection() {
        return direction;
    }
    public Long getAreaFrom() {
        return areaFrom;
    }
    public Long getAreaTo() {
        return areaTo;
    }
    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }
    public Long getRentPriceTo() {
        return rentPriceTo;
    }
    public String getLevel() {
        return level;
    }
    public Long getStaffID() {
        return staffID;
    }

    public static class Builder {
        private String name;
        private String ward;
        private String district;
        private String street;
        private Long numberOfBasement;
        private List<String> typeCode = new ArrayList<>();
        private String managerName;
        private String managerPhone;
        private Long floorArea;
        private String direction;
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String level;
        private Long staffID;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Builder setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setStaffID(Long staffID) {
            this.staffID = staffID;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
