import React from "react";
import styled from "styled-components";
import { HomeHeader, HomeRecommendGrid } from "../components/Home";

/**
 * 홈
 */
const Home = (): JSX.Element => {
  return (
    <StyledDiv>
      <HomeHeader />
      <HomeRecommendGrid />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 96px 16px 24px;
`;

export default Home;
