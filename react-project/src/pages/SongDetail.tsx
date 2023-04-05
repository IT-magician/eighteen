import React from "react";
import styled from "styled-components";
import { BackButton } from "../components/common/button";
import { SongDetailInfo } from "../components/SongDetail";

/**
 * 곡 상세 화면
 */
const SongDetail = (): JSX.Element => {
  return (
    <StyledDiv>
      <div className="back-button">
        <BackButton />
      </div>
      <SongDetailInfo />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  padding: 32px;

  & > .back-button {
    position: absolute;
    top: 48px;
    left: 32px;
  }
`;

export default SongDetail;
