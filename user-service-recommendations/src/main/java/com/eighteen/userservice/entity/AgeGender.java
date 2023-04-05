package com.eighteen.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "age_gender")
public class AgeGender {

    @Id
    @Column(name = "ag_id")
    private String agId;

    @OneToMany(mappedBy = "ageGender", cascade = CascadeType.ALL)
    private List<Ranking> rankings = new ArrayList<>();
}