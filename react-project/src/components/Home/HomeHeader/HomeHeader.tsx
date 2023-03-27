import React from "react";
import { useRecoilValue } from "recoil";
import styled from "styled-components";
import { userState } from "../../../recoil/atom";

const HomeHeader = (): JSX.Element => {
  const user = useRecoilValue(userState);

  return (
    <StyledHeader>
      <h1>
        <span>
          <span>{user?.nickname || "사용자"}</span>님
        </span>
        <br />
        오늘도 한 번 질러볼까요?
      </h1>
    </StyledHeader>
  );
};

const StyledHeader = styled.header`
  box-sizing: border-box;
  width: 100%;
  padding: 8px;
  & > h1 {
    font-size: 32px;
    font-weight: 400;
    & > span {
      font-size: 24px;
      line-height: 32px;
      & > span {
        font-weight: 900;
      }
    }
  }
`;

export default HomeHeader;
