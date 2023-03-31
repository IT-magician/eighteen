package com.edu.ssafy.search.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongWithPreferableDTO extends SongInfoDTO {
    boolean isPreferable;


    @Override
    public String toString() {
        String str = super.toString();
        return "ChildDTO{" +
                "isPreferable='" + isPreferable + '\'' +
                '}'+str;
    }
}
