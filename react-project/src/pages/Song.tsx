import React from "react";
import styled from "styled-components";
import { SongResultList, SongSearchComponent } from "../components/Song";

/**
 * 전체 곡 목록
 */
const Song = (): JSX.Element => {
  return (
    <StyledDiv>
      <h1>노래 찾기</h1>
      <SongSearchComponent />
      <SongResultList />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  padding: 96px 16px 80px;
  & > h1 {
    font-weight: 400;
    font-size: 32px;
  }
`;

export default Song;
