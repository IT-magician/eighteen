import React from "react";
import styled from "styled-components";
import { login } from "../../../apis/oauth";

const GoogleLoginButton = (): JSX.Element => {
  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        login("google");
      }}
    >
      <div className="imgDiv">
        <img src={`${process.env.PUBLIC_URL}/google_logo.png`} alt="로고" className="logo"></img>
      </div>
      <div className="labelDiv">Google로 시작하기</div>
    </LoginButton>
  );
};

// css
const LoginButton = styled.button`
  box-sizing: border-box;
  margin-bottom: 16px;
  padding: 0px 8px 0px;
  width: 100%;
  height: 50px;
  border-radius: 12px;
  border: 0px;
  background: #ffffff;
  display: flex;
  align-items: center;
  font: inherit;

  & .imgDiv {
    height: 90%;
    aspect-ratio: 1/1;
    display: flex;
    justify-content: center;
    align-items: center;

    & .logo {
      width: 60%;
      height: 60%;
    }
  }

  & .labelDiv {
    width: 100%;
    font-size: 16px;
  }
`;

export default GoogleLoginButton;
