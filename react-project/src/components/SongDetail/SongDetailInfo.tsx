import React from "react";
import styled from "styled-components";
import { IconButton } from "../common/button";

const SongDetailInfo = (): JSX.Element => {
  const onClickYoutube = () => {
    // 임시 링크
    window.location.href = "https://www.youtube.com/user/ziller/search?query=82072";
  };
  return (
    <StyledDiv>
      <img />
      <div className="main-info">
        <IconButton type={"youtube"} onClick={onClickYoutube} />
        <div>
          <span className="music-id">music id</span>
          <h1>title</h1>
          <span className="singer">singer</span>
        </div>
        <IconButton type={"favorite"} onClick={() => console.log("favorite")} />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  margin-top: 144px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  & > img {
    width: 100%;
    max-width: 296px;
    aspect-ratio: 1;
    background-color: var(--black-opacity);
    border: 0;
    box-shadow: var(--shadow);
  }
  & > .main-info {
    margin-top: 24px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;

    & > div {
      text-align: center;
      & > .music-id {
        font-size: 16px;
        color: var(--blue-500);
        font-weight: 900;
      }
      & > .singer {
        font-size: 20px;
      }
      & > h1 {
        margin: 16px auto;
        font-size: 24px;
      }
    }
  }
`;

export default SongDetailInfo;
