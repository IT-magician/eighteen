import React, { useState } from "react";
import styled from "styled-components";
import RegisterProfile from "../components/Register/RegisterProfile/RegisterProfile";
import RegisterFavoriteSetting from "../components/Register/RegisterFavoriteSetting/RegisterFavoriteSetting";
import { EnjoyHandBg } from "../components/common/background";
import RegisterFinalSilde from "../components/Register/RegisterFinalSilde";

const Register = (): JSX.Element => {
  const [page, setPage] = useState<number>(0);
  return (
    <StyledDiv>
      {page === 0 && <RegisterProfile nextPage={() => setPage(1)} />}
      {page === 1 && <RegisterFavoriteSetting nextPage={() => setPage(2)} />}
      {page === 2 && <RegisterFinalSilde />}
      <EnjoyHandBg show={page === 2} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  height: 100vh;
  width: 100vw;
  position: relative;
  overflow: hidden;
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
  & > div:last-child {
    left: -16px;
  }
`;

export default Register;
