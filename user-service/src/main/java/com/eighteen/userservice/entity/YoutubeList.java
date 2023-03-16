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
@Table(name = "youtube_lists")
public class YoutubeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_list_id")
    private Integer youtubeListId;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name = "thumbnail_img_url")
    private String thumbnailImgUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "youtuber")
    private String youtuber;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
