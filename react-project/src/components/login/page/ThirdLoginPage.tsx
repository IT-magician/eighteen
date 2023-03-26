import React from "react";
import styled from "styled-components";

/**
 * 3번째 로그인 화면
 */
const ThirdLoginPage = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <p>노래찾기부터</p>
        <p>애창곡 모아보기까지</p>
      </div>
      <p>당신의 노래방을 더 즐겁게 만들어드려요</p>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;

  & > div {
    box-sizing: border-box;
    width: 100%;
    margin: 160px 0px 0px 0px;
    padding: 0px 0px 0px 28px;

    & > p {
      margin: 0px;
      font-size: 32px;
    }
  }

  & > p {
    box-sizing: border-box;
    width: 100%;
    margin: 40px 0px 0px 0px;
    padding: 0px 0px 0px 28px;
  }
`;
export default ThirdLoginPage;
