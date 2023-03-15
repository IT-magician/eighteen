package com.eighteen.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "youtube_list")
public class YoutubeList {

    @Id
    @Column(name = "youtube_list_id")
    private Integer youtubeListId;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
