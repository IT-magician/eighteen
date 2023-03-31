package com.eighteen.userservice.dto.request;

import com.eighteen.userservice.entity.Music;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "RequestGetEighteenDto", description = "RequestGetEighteenDto")
public class RequestGetEighteenDto {

    @ApiModelProperty(value = "페이지", required = true)
    private Integer page;

    @ApiModelProperty(value = "사이즈", required = true)
    private Integer size;

}
