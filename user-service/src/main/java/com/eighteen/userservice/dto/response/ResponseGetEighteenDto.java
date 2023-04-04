package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.entity.MyEighteen;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "ResponseGetEighteenDto", description = "ResponseGetEighteenDto")
public class ResponseGetEighteenDto {

    @ApiModelProperty(value = "애창곡 페이지", required = true)
    private Page<MusicDto> musicPage;

}
