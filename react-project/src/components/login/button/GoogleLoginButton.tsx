import React from "react";
import styled from "styled-components";
import { instance } from "../../../apis";

const GoogleLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `http://j8b304.p.ssafy.io/oauth2/authorization/${socialType}`;
  };

  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        window.location.href = socialSignIn("google");
      }}
    >
      Google 로그인
    </LoginButton>
  );
};

// css
const LoginButton = styled.button``;

export default GoogleLoginButton;
