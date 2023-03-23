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
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Integer weatherId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "weather", cascade = CascadeType.ALL)
    private List<WRecc> wReccs = new ArrayList<>();
}
