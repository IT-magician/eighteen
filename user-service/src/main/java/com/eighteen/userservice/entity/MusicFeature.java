package com.eighteen.userservice.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "music_feature")
public class MusicFeature {

    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "tempo")
    private Float tempo;

    @Column(name = "dance_ability")
    private Float dance_ability;

    @Column(name = "energy")
    private Float energy;

    @Column(name = "valence")
    private Float valence;

    @Column(name = "mood")
    private Float mood;

    @Column(name = "`key`")
    private Integer key;

    @Column(name = "high_pitch")
    private String highPitch;

    @Column(name = "low_pitch")
    private String lowPitch;

    @OneToOne
    @MapsId
    @JoinColumn(name = "music_id")
    private Music music;
}
