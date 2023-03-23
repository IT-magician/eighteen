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

    private String music_highPitch;

    private String music_lowPitch;

    private Integer key;

    private String user_highPitch;

    private String user_lowPitch;
    public ResponseMusicDetailDto(User user, Music music, MusicFeature musicFeature) {

        this.musicId = music.getMusicId();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.music_highPitch = musicFeature.getHighPitch();
        this.music_lowPitch = musicFeature.getLowPitch();
        this.key = musicFeature.getKey();
        this.user_highPitch = user.getHighPitch();
        this.user_lowPitch = user.getLowPitch();
    }
}
