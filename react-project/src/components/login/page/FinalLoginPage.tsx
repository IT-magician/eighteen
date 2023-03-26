import React from "react";
import styled from "styled-components";
import { KakaoLoginButton, GoogleLoginButton, NaverLoginButton } from "../button";

/**
 * 4번째 로그인 화면
 */
const Login = (): JSX.Element => {
  return (
    <StyledDiv>
      <p>너도 몰랐던 너의 에이틴</p>
      <div>
        <p>에이틴과 함께</p>
        <p>신나는 노래방 라이프를 즐기러 가볼까요?</p>
      </div>
      <div className="kakaoButton">
        <KakaoLoginButton />
      </div>
      <div className="googleButton">
        <GoogleLoginButton />
      </div>
      <div className="naverButton">
        <NaverLoginButton />
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

  & .kakaoButton {
  }

  & .googleButton {
  }

  & .naverButton {
  }
`;
export default Login;
