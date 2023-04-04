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

    @Column(name = "email")
    private String email;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "profileImage")
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyEighteen> myEighteens = new ArrayList<>();

    public void updateImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void updateProfile(RequestUpdateProfileDto requestUpdateProfileDto) {

        this.nickname = requestUpdateProfileDto.getNickname();
        this.birth = requestUpdateProfileDto.getBirth();
        this.gender = requestUpdateProfileDto.getGender();
    }
}
