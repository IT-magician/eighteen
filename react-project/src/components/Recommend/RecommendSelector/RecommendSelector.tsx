import React from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import RecommendEmotionSelector from "./RecommendEmotionSelector";
import RecommendRankingSelector from "./RecommendRankingSelector";
import RecommendSituationSelector from "./RecommendSituationSelector";

const RecommendSelector = (): JSX.Element => {
  const { type } = useParams();

  return (
    <StyledDiv>
      {type === "ranking" && <RecommendRankingSelector />}
      {type === "emotion" && <RecommendEmotionSelector />}
      {type === "situation" && <RecommendSituationSelector />}
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default RecommendSelector;
