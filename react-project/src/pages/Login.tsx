import React from "react";
import styled from "styled-components";
import { KakaoLoginButton, GoogleLoginButton, NaverLoginButton } from "../components/login/button";

/**
 * 로그인 화면
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
    position: absolute;
    left: 28px;
    top: 160px;
    margin: 0px;
    font-size: 32px;
  }

  & > div {
    position: absolute;
    left: 28px;
    top: 235px;

    & > p {
      margin: 0px;
      font-size: 24px;
    }
  }

  & .kakaoButton {
    position: absolute;
    left: 39px;
    top: 714px;
  }

  & .googleButton {
    position: absolute;
    left: 39px;
    top: 614px;
  }

  & .naverButton {
    position: absolute;
    left: 39px;
    top: 514px;
  }
`;
export default Login;
