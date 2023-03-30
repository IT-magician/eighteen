import React from "react";
import styled from "styled-components";
import RegisterProfile from "../components/Register/RegisterProfile";

const Register = (): JSX.Element => {
  return (
    <StyledDiv>
      <RegisterProfile />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default Register;
