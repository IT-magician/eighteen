package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "ResponseProfileDto", description = "ResponseProfileDto")
public class ResponseProfileDto {

    private String nickname;

    private String birth;

    private String gender;

    private String profileImage;

    public ResponseProfileDto(User user) {

        this.nickname = user.getNickname();
        this.birth = user.getBirth();
        this.gender = user.getGender();
        this.profileImage = user.getProfileImage();
    }
}
