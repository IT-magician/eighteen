import React from "react";
import styled from "styled-components";
import { KakaoLoginButton, GoogleLoginButton } from "../components/login/button";

/**
 * 로그인 화면
 */
const Login = (): JSX.Element => {
  return (
    <Container>
      <TitleText>너도 몰랐던 너의 에이틴</TitleText>
      <SubtitleDiv>
        <SubtitleText>에이틴과 함께</SubtitleText>
        <SubtitleText>신나는 노래방 라이프를 즐기러 가볼까요?</SubtitleText>
      </SubtitleDiv>
      <KakaoLoginButton></KakaoLoginButton>
      <GoogleLoginButton></GoogleLoginButton>
    </Container>
  );
};

const Container = styled.div`
  position: relative;
`;

const TitleText = styled.p`
  position: absolute;
  left: 28px;
  top: 160px;
  margin: 0px;
  font-size: 32px;
`;

const SubtitleDiv = styled.div`
  position: absolute;
  left: 28px;
  top: 235px;
`;

const SubtitleText = styled.p`
  margin: 0px;
  font-size: 24px;
`;
export default Login;
