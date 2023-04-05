package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.dto.MusicDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRecommendDto {

    private List<MusicDto> recommendedMusics;
}
