import React from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import { Weather } from "../../../recoil/atom/weatherState";
import RecommendEmotionSelector from "./RecommendEmotionSelector";
import RecommendRankingSelector from "./RecommendRankingSelector";
import RecommendSituationSelector from "./RecommendSituationSelector";
import RecommendWeatherSelector from "./RecommendWeatherSelector";

interface Props {
  weather: Weather;
}

const RecommendSelector = ({ weather }: Props): JSX.Element => {
  const { type } = useParams();

  return (
    <StyledDiv>
      {type === "weather" && <RecommendWeatherSelector weather={weather} />}
      {type === "ranking" && <RecommendRankingSelector />}
      {type === "emotion" && <RecommendEmotionSelector />}
      {type === "situation" && <RecommendSituationSelector />}
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default RecommendSelector;
