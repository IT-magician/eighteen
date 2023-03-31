package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "ResponseInfoDto", description = "ResponseInfoDto")
public class ResponseInfoDto {

    private String nickname;

    private String birth;

    private String gender;

    private String profileImage;

    public ResponseInfoDto(User user) {

        this.nickname = user.getNickname();
        this.birth = user.getBirth();
        this.gender = user.getGender();
        this.profileImage = user.getProfileImage();
    }
}
