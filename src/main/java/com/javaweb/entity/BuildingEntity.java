package com.javaweb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "building")
public class BuildingEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name="rentpricedescription")
    private String rentPriceDescription;

    @Column(name="servicefee")
    private String serviceFee;

    @Column(name="carfee")
    private String carFee;

    @Column(name="motofee")
    private String motoFee;

    @Column(name="overtimefee")
    private String overtimeFee;

    @Column(name="waterfee")
    private String waterFee;

    @Column(name="electricityfee")
    private String electricityFee;

    @Column(name="deposit")
    private String deposit;

    @Column(name="payment")
    private String payment;

    @Column(name="renttime")
    private String rentTime;

    @Column(name="decorationtime")
    private String decorationTime;

    @Column(name="brokeragefee")
    private Double brokerAgeFee;

    @Column(name = "type")
    private String type;

    @Column(name="note")
    private String note;

    @Column(name="linkofbuilding")
    private String linkOfBuilding;

    @Column(name="map")
    private String map;

    @Column(name="avatar")
    private String avatar;

    @Column(name="managername")
    private String managerName;

    @Column(name="managerphone")
    private String managerPhone;

    @OneToMany(mappedBy = "buildingRentArea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

//    @OneToMany(mappedBy = "buildingAssign", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffs = new ArrayList<>();

}
