package com.eighteen.userservice.entity;

import javax.persistence.*;

import com.eighteen.userservice.dto.request.RequestUpdateProfileDto;
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
    private String userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "high_pitch")
    private String highPitch;

    @Column(name = "low_pitch")
    private String lowPitch;

    @Column(name = "vocal")
    private String vocal;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyEighteen> myEighteens = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<URecc> uReccs = new ArrayList<>();

    public void updateImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void updateProfile(RequestUpdateProfileDto requestUpdateProfileDto) {

        this.nickname = requestUpdateProfileDto.getNickname();
        this.birth = requestUpdateProfileDto.getBirth();
        this.gender = requestUpdateProfileDto.getGender();
    }
}
