import React from "react";
import styled from "styled-components";

/**
 * 2번째 로그인 화면
 */
const SecondLoginPage = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>오늘 나에게 어울리는 노래</p>
      <div>
        <p>기분, 상황, 날씨별</p>
        <p>다양한 상황에 어울리는 노래를</p>
        <p>추천해드려요</p>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;

  & > p {
    box-sizing: border-box;
    width: 100%;
    margin: 160px 0px 0px 0px;
    padding: 0px 0px 0px 28px;
    font-size: 32px;
  }

  & > div {
    box-sizing: border-box;
    width: 100%;
    margin: 40px 0px 0px 0px;
    padding: 0px 0px 0px 28px;

    & > p {
      margin: 0px;
      font-size: 24px;
    }
  }
`;
export default SecondLoginPage;
