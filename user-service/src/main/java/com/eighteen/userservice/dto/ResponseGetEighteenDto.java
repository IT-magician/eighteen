package com.eighteen.userservice.dto;

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
@ApiModel(value = "ResponseGetEighteenDto", description = "ResponseGetEighteenDto")
public class ResponseGetEighteenDto {

    @ApiModelProperty(value = "애창곡 목록", required = true)
    private List<MyEighteen> myEighteens;

    @ApiModelProperty(value = "빠른선곡 목록", required = true)
    private List<MyEighteen> quicks;
}
