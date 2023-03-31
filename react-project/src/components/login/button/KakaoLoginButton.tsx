import React from "react";
import styled from "styled-components";
import { login } from "../../../apis/oauth";

const KakaoLoginButton = (): JSX.Element => {
  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        login("kakao");
      }}
    >
      <div className="imgDiv">
        <img src={`${process.env.PUBLIC_URL}/kakao_logo.png`} alt="로고" className="logo"></img>
      </div>
      <div className="labelDiv">카카오로 시작하기</div>
    </LoginButton>
  );
};

const LoginButton = styled.button`
  box-sizing: border-box;
  margin-bottom: 16px;
  padding: 0px 8px 0px;
  width: 100%;
  height: 50px;
  border-radius: 12px;
  border: 0px;
  background: #fee500;
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
    color: #191919;
    font-size: 16px;
  }
`;

export default KakaoLoginButton;
