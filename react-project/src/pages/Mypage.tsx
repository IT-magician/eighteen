import React from "react";
import styled from "styled-components";
import Profile from "../components/mypage/Profile";

/**
 * 마이페이지
 */
const Mypage = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <p>마이페이지</p>
        <Profile />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;

  & > div {
    position: absolute;
    left: 28px;
    top: 184px;

    & > p {
      margin: 0px 0px 40px;
      font-size: 32px;
    }
  }
`;

export default Mypage;
