import React from "react";
import styled from "styled-components";
import SongSample from "../../../utils/sample/song.json";
import { SongItem } from "../../common/song";

const SongResultDefault = (): JSX.Element => {
  return (
    <StyledDiv>
      <ul>
        {SongSample.data.map((item, index) => (
          <SongItem
            key={index}
            musicId={item.musicId}
            title={item.title}
            singer={item.singer}
            isEighteen={item.isEighteen}
            thumbnailUrl={item.thumbnailUrl}
          />
        ))}
      </ul>
      <div>
        <img src={`${process.env.PUBLIC_URL}/icon/search.png`} />
        <p>
          제목, 가수명에 대한 검색어로 <br />
          원하는 노래를 찾아보세요
        </p>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  & > div {
    position: absolute;
    top: 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    line-height: 24px;
    & img {
      width: 40%;
      transform: rotate(60deg);
    }
    & p {
      margin: 0;
    }
  }
  & > ul {
    pointer-events: none;
    filter: blur(4px);
    margin: 0;
    padding: 0;
    height: 100%;
    overflow: hidden;
  }
`;

export default SongResultDefault;
