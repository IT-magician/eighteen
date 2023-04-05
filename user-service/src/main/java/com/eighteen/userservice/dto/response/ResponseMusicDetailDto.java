package com.eighteen.userservice.dto.response;

import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MusicFeature;
import com.eighteen.userservice.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ApiModel(value = "ResponseMusicDetailDto", description = "ResponseMusicDetailDto")
public class ResponseMusicDetailDto {

    private Integer musicId;

    private String title;

    private String singer;

    private String thumbnailUrl;

    private String youtubeUrl;

    private Integer key;

    private Boolean isEighteen;

    public ResponseMusicDetailDto(User user, Music music, MusicFeature musicFeature) {

        this.musicId = music.getMusicId();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.thumbnailUrl = music.getThumbnailUrl();
        this.youtubeUrl = music.getYoutubeUrl();
        this.key = musicFeature.getKey();
    }
}
