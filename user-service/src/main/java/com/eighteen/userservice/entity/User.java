package com.eighteen.userservice.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "high_pitch", nullable = false)
    private String highPitch;

    @Column(name = "low_pitch", nullable = false)
    private String lowPitch;

    @Column(name = "vocal", nullable = false)
    private String vocal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyEighteen> myEighteens = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<URecc> uReccs = new ArrayList<>();

}
