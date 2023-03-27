import React from "react";
import styled from "styled-components";
import { Weather } from "../../../recoil/atom/weatherState";
import SampleData from "../../../utils/sample/song.json";
import { SongSlideList } from "../../common/song";
import { RecommendSelector } from "../RecommendSelector";

interface Props {
  weather: Weather;
}

const RecommendList = ({ weather }: Props): JSX.Element => {
  return (
    <StyledDiv>
      <SongSlideList songList={SampleData.data} />
      <RecommendSelector weather={weather} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  & > div:first-child {
    margin-top: 96px;
  }
`;

export default RecommendList;
