package com.eighteen.userservice.dto;

import com.eighteen.userservice.entity.Music;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "MusicDto", description = "MusicDto")
public class MusicDto {

    @ApiModelProperty(value = "노래 번호", required = true)
    private Integer musicId;

    @ApiModelProperty(value = "노래 제목", required = true)
    private String title;

    @ApiModelProperty(value = "가수", required = true)
    private String singer;

    @ApiModelProperty(value = "썸네일", required = true)
    private String thumbnailUrl;

    @ApiModelProperty(value = "애창곡 여부", required = true)
    private Boolean isEighteen;

    public MusicDto(Music music, Boolean isEighteen) {

        this.musicId = music.getMusicId();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.thumbnailUrl = music.getThumbnailUrl();
        this.isEighteen = isEighteen;
    }
}
