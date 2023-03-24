import React from "react";
import styled from "styled-components";
import { SongItem } from "../../common/song";

interface Music {
  isEighteen: boolean;
  musicId: number;
  singer: string;
  thumbnailUrl: string;
  title: string;
}

interface Props {
  musicList: Music[];
}

/**
 * 최근 본 노래 페이지
 * 최근에 방문했던 노래 상세 페이지 순으로 SongItem을 보여주는 역할을 합니다.
 */
const SongHistory = ({ musicList }: Props): JSX.Element => {
  if (musicList) {
    console.log(musicList);
  }

  return (
    <StyledDiv>
      <p>최근 본 노래</p>
      <ul>
        {musicList.map((music) => (
          <SongItem
            key={music.musicId}
            musicId={music.musicId}
            title={music.title}
            singer={music.singer}
            isEighteen={music.isEighteen}
          />
        ))}
      </ul>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & > p {
    margin-top: 0px;
    font-size: 24px;
  }

  & > ul {
    padding: 0px;
  }
`;

export default SongHistory;
