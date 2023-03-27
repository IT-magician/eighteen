import React from "react";
import styled from "styled-components";
import { getWeatherImg } from "../../../apis/weather";
import { Weather } from "../../../recoil/atom/weatherState";

interface Props {
  weather: Weather;
}

const RecommendWeatherSelector = ({ weather }: Props): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <span>{weather.temperature}°C</span>
        <h3>{weather.text}</h3>
      </div>
      <img src={`${process.env.PUBLIC_URL}/weather/${getWeatherImg(weather.weather)}.png`} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: absolute;
  right: 16px;
  top: -96px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  & > div {
    margin-right: 16px;
    display: flex;
    flex-direction: column;
    align-items: center;
    & > span {
      letter-spacing: 0;
    }
    & > h3 {
      margin: 8px;
    }
  }
  & > img {
    max-width: 80px;
    width: 100%;
  }
`;

export default RecommendWeatherSelector;
