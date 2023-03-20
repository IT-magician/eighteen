import React, { useRef } from "react";
import { GoogleOAuthProvider, GoogleLogin } from "@react-oauth/google";
import styled from "styled-components";

const GoogleLoginButton = (): JSX.Element => {
  const googleRef = useRef<HTMLDivElement>(null);
  console.log(googleRef.current);

  const clickEvent = () => {
    console.log("success");
  };

  const loginHandler = () => {
    console.log("PrimaryButton을 경유해서 clickEvent 동작");
    if (googleRef.current) {
      googleRef.current.click();
    }
  };

  return (
    <div className="container">
      <GoogleLogin ref={googleRef} id="google-login-api"></GoogleLogin>
      <LoginButton onClick={loginHandler}>Google 로그인</LoginButton>
    </div>
  );
};

// css
const GoogleLogin = styled.div`
  width: 222px;
  height: 40px;
`;

const LoginButton = styled.button``;

export default GoogleLoginButton;
