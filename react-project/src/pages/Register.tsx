import React from "react";
import styled from "styled-components";
import RegisterProfile from "../components/Register/RegisterProfile/RegisterProfile";

const Register = (): JSX.Element => {
  return (
    <StyledDiv>
      <RegisterProfile />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  padding: 120px 32px 0;
  & h1,
  & h2 {
    font-weight: 400;
    & > span {
      margin: 8px 0px;
      display: block;
      word-break: keep-all;
    }
  }
  & h1 {
    font-size: 28px;
    margin-bottom: 32px;
    min-height: 40px;
  }
  & h2 {
    font-size: 20px;
  }
`;

export default Register;
