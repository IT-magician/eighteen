import React from "react";
import styled from "styled-components";
import { instance } from "../../../apis";

const GoogleLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `http://192.168.31.73:8080/oauth2/authorization/${socialType}`;
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
