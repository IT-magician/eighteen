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
@Table(name = "e_music")
public class EMusic {

    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @Column(name = "popularity")
    private Integer popularity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "music_id")
    private Music music;
}
