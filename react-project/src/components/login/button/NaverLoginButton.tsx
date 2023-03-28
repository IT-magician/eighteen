import React from "react";
import styled from "styled-components";

const NaverLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `http://j8b304.p.ssafy.io/oauth2/authorization/${socialType}}`;
  };

  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        window.location.href = socialSignIn("naver");
      }}
    >
      <img src={`${process.env.PUBLIC_URL}/naver_logo.png`} alt="로고" className="logo"></img>
      <div>네이버로 시작하기</div>
    </LoginButton>
  );
};

// css
const LoginButton = styled.button`
  box-sizing: border-box;
  padding: 0px 8px 0px;
  width: 240px;
  height: 50px;
  border-radius: 12px;
  border: 0px;
  background: #03c75a;
  display: flex;
  align-items: center;
  font: inherit;

  & .logo {
    height: 90%;
    aspect-ratio: 1/1;
  }

  & > div {
    width: 100%;
    font-size: 16px;
    color: #ffffff;
    font: inherit;
  }
`;

export default NaverLoginButton;
