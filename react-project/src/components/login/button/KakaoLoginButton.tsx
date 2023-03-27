import React from "react";
import styled from "styled-components";

const KakaoLoginButton = (): JSX.Element => {
  const socialSignIn = (socialType: string) => {
    return `http://j8b304.p.ssafy.io/oauth2/authorization/${socialType}`;
  };

  return (
    <div className="container">
      <LoginButton
        onClick={(e) => {
          e.preventDefault();
          window.location.href = socialSignIn("kakao");
        }}
      >
        <Symbol></Symbol>
        <Label>카카오로 시작하기</Label>
      </LoginButton>
    </div>
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
`;

const Symbol = styled.div``;

const Label = styled.div`
  height: 30%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  font-weight: bold;
`;

export default KakaoLoginButton;
