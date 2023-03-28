import React from "react";
import styled from "styled-components";

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
      <div className="imgDiv">
        <img src={`${process.env.PUBLIC_URL}/google_logo.png`} alt="로고" className="logo"></img>
      </div>
      <div className="labelDiv">Google로 시작하기</div>
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
  background: #ffffff;
  display: flex;
  align-items: center;
  font: inherit;

  & .imgDiv {
    height: 90%;
    aspect-ratio: 1/1;
    display: flex;
    justify-content: center;
    align-items: center;

    & .logo {
      width: 60%;
      height: 60%;
    }
  }

  & .labelDiv {
    width: 100%;
    font-size: 16px;
  }
`;

export default GoogleLoginButton;
