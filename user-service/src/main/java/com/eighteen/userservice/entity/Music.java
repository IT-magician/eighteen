package com.eighteen.userservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "music")
public class Music {

    @Id
    @Column(name = "music_id")
    private Integer musicId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "singer", nullable = false)
    private String singer;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @OneToOne(mappedBy = "music", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MusicFeature musicFeature;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<MyEighteen> myEighteens = new ArrayList<>();

}
