import React from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import { Weather } from "../../../recoil/atom/weatherState";
import { SongSildeList } from "../../common/song";

interface Props {
  weather: Weather;
}

const RecommendList = ({ weather }: Props): JSX.Element => {
  const { type } = useParams();

  return (
    <StyledDiv>
      테스트
      <SongSildeList musicList={[]} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default RecommendList;
