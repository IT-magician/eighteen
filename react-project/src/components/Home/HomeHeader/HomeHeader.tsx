import React from "react";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { userState } from "../../../recoil/atom";

const HomeHeader = (): JSX.Element => {
  const [user, setUser] = useRecoilState(userState);

  return (
    <StyledHeader>
      <h1>
        <span></span>
      </h1>
    </StyledHeader>
  );
};

const StyledHeader = styled.header``;

export default HomeHeader;
