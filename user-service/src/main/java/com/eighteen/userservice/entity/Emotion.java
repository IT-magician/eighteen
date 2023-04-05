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
@Table(name = "emotion")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Integer emotionId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "emotion", cascade = CascadeType.ALL)
    private List<EMusic> eMusics = new ArrayList<>();
}
