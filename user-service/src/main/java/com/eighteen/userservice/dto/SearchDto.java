package com.eighteen.userservice.dto;

import com.eighteen.userservice.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    int id;

    String title;

    String singer;

    String youtube_url;

    public SearchDto(Music music) {

        this.id = music.getMusicId();
        this.title = music.getTitle();
        this.singer = music.getSinger();
        this.youtube_url = music.getYoutubeUrl();
    }
}
