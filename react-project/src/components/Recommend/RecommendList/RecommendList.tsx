import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { getEighteenRanking, recommendSong } from "../../../apis/recommend";
import { RecommendType } from "../../../apis/recommend/type";
import { getWeatherIdx } from "../../../apis/weather";
import { authState } from "../../../recoil/atom/authState";
import { Weather } from "../../../recoil/atom/weatherState";
import SampleData from "../../../utils/sample/song.json";
import { Song, SongSlideList } from "../../common/song";
import { RecommendSelector } from "../RecommendSelector";

interface Props {
  weather: Weather;
}

const RecommendList = ({ weather }: Props): JSX.Element => {
  const [auth, setAuth] = useRecoilState(authState);
  const [songList, setSongList] = useState<Song[]>([]);
  const { type } = useParams();
  const location = useLocation();

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const gender = searchParams.get("gender") ?? "M";
    const age = searchParams.get("age") ?? "1";
    const emotion = searchParams.get("emotion") ?? "1";
    const situation = searchParams.get("situation") ?? "1";

    const getRankingList = async () => {
      try {
        const { data } = await getEighteenRanking(Number(age), gender, auth.token);
        console.dir(data);
        setSongList(data.musicDtos);
      } catch (e) {
        if (axios.isAxiosError(e)) {
          if (e.response?.status === 401) {
            setAuth({ ...auth, token: "" });
          }
        }
      }
    };
    const getRecommendList = async (type: RecommendType, id?: string) => {
      try {
        const { data } = await recommendSong(type, auth.token, id);
        console.dir(data);
        setSongList(data.recommendedMusics);
      } catch (e) {
        if (axios.isAxiosError(e)) {
          if (e.response?.status === 401) {
            setAuth({ ...auth, token: "" });
          }
        }
      }
    };

    switch (type) {
      case "ranking":
        if (!location.search) return;
        getRankingList();
        return;
      case "situation":
        if (!location.search) return;
        getRecommendList(type, situation);
        return;
      case "emotion":
        if (!location.search) return;
        getRecommendList(type, emotion);
        return;
      case "weather":
        getRecommendList(type, "" + getWeatherIdx(weather.weather));
        return;
      case "myEighteen":
        getRecommendList(type);
        return;
    }
  }, [location]);
  return (
    <StyledDiv>
      <SongSlideList songList={songList} ranking={type === "ranking"} />
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
