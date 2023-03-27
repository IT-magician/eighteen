import React from "react";
import styled from "styled-components";

const KakaoLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/${socialType}}`;
  };

  return (
    <LoginButton
      onClick={(e) => {
        e.preventDefault();
        window.location.href = socialSignIn("kakao");
      }}
    >
      <div></div>
      <p>카카오로 시작하기</p>
    </LoginButton>
  );
};

const LoginButton = styled.button`
  background: #fee500;
  width: 222px;
  height: 50px;
  color: #191919;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;

  & > div {
  }

  & > p {
    height: 30%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 16px;
    font-weight: bold;
  }
`;

export default KakaoLoginButton;
