import React from "react";
import styled from "styled-components";

const GoogleLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `http://localhost:8080/oauth2/authorization/${socialType}}`;
  };

  return (
    <div>
      <LoginButton
        onClick={(e) => {
          e.preventDefault();
          window.location.href = socialSignIn("google");
        }}
      >
        Google 로그인
      </LoginButton>
    </div>
  );
};

// css
const LoginButton = styled.button``;

export default GoogleLoginButton;
