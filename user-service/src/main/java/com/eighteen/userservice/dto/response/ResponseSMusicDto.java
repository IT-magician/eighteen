package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.dto.MusicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSMusicDto {

    private List<MusicDto> recommendedMusics;
}
