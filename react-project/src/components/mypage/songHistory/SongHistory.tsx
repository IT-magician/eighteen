import React from "react";
import styled from "styled-components";
import SongHistoryList from "./SongHistoryList";

/**
 * 최근 본 노래 페이지
 * 최근에 방문했던 노래 상세 페이지 순으로 SongItem을 보여주는 역할을 합니다.
 */
const SongHistory = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>최근 본 노래</p>
      <div>
        <SongHistoryList />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & > p {
    margin-top: 0px;
    font-size: 24px;
  }
`;

export default SongHistory;
