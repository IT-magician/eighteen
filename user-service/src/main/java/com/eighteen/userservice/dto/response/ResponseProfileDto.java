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

    private String email;

    private String birth;

    private String gender;

    private String profileImage;
}
