package com.javaweb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staffs;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingAssign;
}
