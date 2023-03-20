import React from "react";
import styled from "styled-components";
import { KakaoLoginButton, GoogleLoginButton } from "../components/login/button";

/**
 * 로그인 화면
 */
const Login = (): JSX.Element => {
  return (
    <StyledDiv>
      <KakaoLoginButton></KakaoLoginButton>
      <GoogleLoginButton></GoogleLoginButton>
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default Login;
