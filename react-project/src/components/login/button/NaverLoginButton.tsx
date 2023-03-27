import React from "react";
import styled from "styled-components";

const NaverLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/${socialType}}`;
  };

  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        window.location.href = socialSignIn("naver");
      }}
    >
      Naver 로그인
    </LoginButton>
  );
};

// css
const LoginButton = styled.button``;

export default NaverLoginButton;
