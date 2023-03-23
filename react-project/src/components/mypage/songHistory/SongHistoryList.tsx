import React from "react";
import styled from "styled-components";
import { SongItem } from "../../common/song";

/**
 * 최근 본 노래 리스트 페이지
 * 노래 아이템 리스트를 분할 후 각각 SongItem 컴포넌트에 할당하는 역할을 합니다.
 */
const SongHistoryList = (): JSX.Element => {
  return (
    <StyledDiv>
      <SongItem />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default SongHistoryList;
