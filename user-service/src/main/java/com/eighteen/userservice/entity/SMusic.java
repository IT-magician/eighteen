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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_music_id")
    private Integer sMusicId;

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Column(name = "popularity")
    private Integer popularity;
}
