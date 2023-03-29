import React from "react";
import styled from "styled-components";
import { IconButton } from "../common/button";

const SongDetailInfo = (): JSX.Element => {
  return (
    <StyledDiv>
      <img />
      <div className="main-info">
        <IconButton type={"youtube"} onClick={() => console.log("youtube")} />
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
  & > .main-info {
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
        font-size: 24px;
      }
      & > h1 {
        margin: 16px auto;
        font-size: 32px;
      }
    }
  }
`;

export default SongDetailInfo;
