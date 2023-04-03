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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_recc_id")
    private Integer eReccId;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Column(name = "popularity")
    private Integer popularity;
}
