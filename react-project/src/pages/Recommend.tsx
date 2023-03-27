import React, { useEffect } from "react";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { getWeather } from "../apis/weather";
import { RecommendHeader, RecommendList } from "../components/Recommend";
import { weatherState } from "../recoil/atom/weatherState";

/**
 * 추천 화면
 */
const Recommend = (): JSX.Element => {
  const [weather, setWeather] = useRecoilState(weatherState);

  useEffect(() => {
    // 날씨 정보가 아직 없으면 데이터를 받아옵니다
    if (!weather) getWeather().then((data) => setWeather(data));
  }, [weather]);

  if (weather)
    return (
      <StyledMain>
        <RecommendHeader weather={weather} />
        <RecommendList weather={weather} />
      </StyledMain>
    );
  else return <StyledMain />;
};

const StyledMain = styled.main`
  display: flex;
  flex-direction: column;
  padding: 96px 16px 24px;
`;

export default Recommend;
