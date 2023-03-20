import React from "react";
import styled from "styled-components";

const KakaoLoginButton = (): JSX.Element => {
  return (
    <div className="container">
      <PrimaryButton>
        <a id="kakao-login-btn">
          <img
            src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
            width="222"
            alt="카카오 로그인 버튼"
          />
        </a>
      </PrimaryButton>
      <LoginButton>카카오 로그인</LoginButton>
    </div>
  );
};

const PrimaryButton = styled.div``;

const LoginButton = styled.button``;

export default KakaoLoginButton;
