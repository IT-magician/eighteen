import React from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

/**
 * 로고 컴포넌트
 * 클릭 시 홈화면으로 이동합니다.
 */
const Logo = (): JSX.Element => {
  const navigate = useNavigate();

  const onClick = () => {
    navigate("/");
  };

  return (
    <StyledDiv onClick={onClick}>
      <p>EIGHTEEN</p>
      <img src={`${process.env.PUBLIC_URL}/eighteen-logo.png`} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  cursor: pointer;
  display: flex;
  align-items: flex-end;
  letter-spacing: 0.1em;
  position: absolute;
  z-index: 1;

  & > p {
    margin: 2px 4px;
    font-size: 14px;
    letter-spacing: 0.1em;
  }

  & > img {
    height: 32px;
  }
`;

export default Logo;
