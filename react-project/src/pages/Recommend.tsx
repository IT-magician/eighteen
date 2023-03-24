import React from "react";
import styled from "styled-components";
import { RecommendHeader, RecommendList } from "../components/Recommend";

/**
 * 추천 화면
 */
const Recommend = (): JSX.Element => {
  return (
    <StyledDiv>
      <RecommendHeader />
      <RecommendList />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default Recommend;
