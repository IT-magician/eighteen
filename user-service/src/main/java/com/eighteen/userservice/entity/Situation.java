package com.eighteen.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "situation")
public class Situation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "situation_id")
    private Integer situationId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "situation", cascade = CascadeType.ALL)
    private List<SRecc> sReccs = new ArrayList<>();
}
