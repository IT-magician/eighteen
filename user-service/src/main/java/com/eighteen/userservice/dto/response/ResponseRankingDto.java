package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.dto.MusicDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "ResponseRankingDto", description = "ResponseRankingDto")
public class ResponseRankingDto {

    private List<MusicDto> musicDtos;

}
