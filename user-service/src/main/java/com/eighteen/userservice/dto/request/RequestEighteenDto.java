package com.eighteen.userservice.dto.request;

import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "RequestEighteenDto", description = "RequestEighteenDto")
public class RequestEighteenDto {

    @ApiModelProperty(value = "노래", required = true)
    private Integer musicId;
}
