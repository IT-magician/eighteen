import React from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import { Weather } from "../../../recoil/atom/weatherState";
import { SongSildeList } from "../../common/song";
import SampleData from "../../../utils/sample/song.json";

interface Props {
  weather: Weather;
}

const RecommendList = ({ weather }: Props): JSX.Element => {
  const { type } = useParams();

  return (
    <StyledDiv>
      테스트
      <SongSildeList songList={SampleData.data} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default RecommendList;
