import React from "react";
import styled from "styled-components";
import Profile from "../components/mypage/Profile";

/**
 * 마이페이지
 */
const Mypage = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>마이페이지</p>
      <div>
        <Profile />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  max-width: 430px;
  position: relative;

  & > p {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 184px;
    margin: 0px 0px 40px;
    font-size: 32px;
  }

  & > div {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 260px;
  }
`;

export default Mypage;
