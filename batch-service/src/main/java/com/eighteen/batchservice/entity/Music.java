package com.eighteen.batchservice.entity;

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
@Table(name = "music")
public class Music {

    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<MyEighteen> myEighteens = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Ranking> rankings = new ArrayList<>();
}
