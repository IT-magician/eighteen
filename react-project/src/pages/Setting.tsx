import React from "react";
import styled from "styled-components";

/**
 * 프로필 수정 화면
 */
const Setting = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>프로필 설정</p>
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
`;

export default Setting;
