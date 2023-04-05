package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@JsonIgnoreProperties({"createdDate","modifiedDate"})

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "MUSIC")
@ToString
public class SongInfoEntity extends BaseTimeEntity {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MUSIC_ID")
    int id;
    String title;
    String singer;
    String youtube_url;
    String thumbnail_url;
}
