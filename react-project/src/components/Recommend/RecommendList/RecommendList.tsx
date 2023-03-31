import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import styled from "styled-components";
import { getEighteenRanking } from "../../../apis/recommend";
import { Weather } from "../../../recoil/atom/weatherState";
import SampleData from "../../../utils/sample/song.json";
import { Song, SongSlideList } from "../../common/song";
import { RecommendSelector } from "../RecommendSelector";

interface Props {
  weather: Weather;
}

const RecommendList = ({ weather }: Props): JSX.Element => {
  const [songList, setSongList] = useState<Song[]>([]);
  const { type } = useParams();
  const location = useLocation();

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const gender = searchParams.get("gender") ?? "M";
    const age = searchParams.get("age") ?? "1";
    const emotion = searchParams.get("emotion") ?? "happy";
    const situation = searchParams.get("situation") ?? "0";

    switch (type) {
      case "ranking":
        if (!location.search) return;
        getEighteenRanking(Number(age), gender).then(({ data }) => setSongList(data.musicDtos));
        return;
      case "weather":
      case "myEighteen":
      case "situation":
      case "emotion":
      default:
        return;
    }
  }, [location]);
  return (
    <StyledDiv>
      <SongSlideList songList={songList} />
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
