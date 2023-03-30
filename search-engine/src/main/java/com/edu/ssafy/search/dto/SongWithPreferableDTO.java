package com.edu.ssafy.search.dto;

import lombok.*;
import lombok.AccessLevel;

import java.sql.Driver;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongWithPreferableDTO extends SongDTO{
    boolean isPreferable;


    @Override
    public String toString() {
        String str = super.toString();
        return "ChildDTO{" +
                "isPreferable='" + isPreferable + '\'' +
                '}'+str;
    }
}
