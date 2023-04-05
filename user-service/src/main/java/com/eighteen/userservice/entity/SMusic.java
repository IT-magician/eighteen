package com.eighteen.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "s_music")
public class SMusic {

    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation;

    @Column(name = "popularity")
    private Integer popularity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "music_id")
    private Music music;
}
