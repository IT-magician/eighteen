package com.eighteen.userservice.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "RequestGetEighteenDto", description = "RequestGetEighteenDto")
public class RequestUpdateProfileDto {

    private String userId;

    private String nickname;

    private String birth;

    private String gender;
}
