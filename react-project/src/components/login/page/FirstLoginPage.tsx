import React from "react";
import styled from "styled-components";

/**
 * 1번째 로그인 화면
 */
const FirstLoginPage = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>만나서 반가워요!</p>
      <div>
        <p>에이틴은 당신도 몰랐던</p>
        <p>당신에게 딱 맞는 애창곡을 찾아드려요</p>
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
export default FirstLoginPage;
