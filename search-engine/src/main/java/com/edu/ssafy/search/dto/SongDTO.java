package com.edu.ssafy.search.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDTO {
    int id;
    String title;
    String singer;
    String youtube_url;
}
