package com.eighteen.userservice.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "RequestUpdateProfileDto", description = "RequestUpdateProfileDto")
public class RequestUpdateProfileDto {

    @ApiModelProperty(value = "유저 닉네임", required = true)
    private String nickname;

    @ApiModelProperty(value = "유저 생일", required = true)
    private String birth;

    @ApiModelProperty(value = "유저 성별", required = true)
    private String gender;
}
