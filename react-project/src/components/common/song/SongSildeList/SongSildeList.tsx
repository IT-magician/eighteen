import React, { useMemo } from "react";
import styled from "styled-components";
import { Swiper, SwiperSlide } from "swiper/react";
import { Song, SongItem } from "../SongItem";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

interface Props {
  songList: Song[];
  size?: number;
}

const SongSildeList = ({ songList, size = 5 }: Props): JSX.Element => {
  const songPage = useMemo(() => {
    return songList.reduce((list: Song[][], song, index) => {
      const listIdx = Math.round(index / size);
      if (index % size) list[listIdx].push(song);
      else list.push([song]);
      return list;
    }, []);
  }, [songList]);

  return (
    <Swiper
      spaceBetween={50}
      slidesPerView={1}
      onSlideChange={() => console.log("slide change")}
      onSwiper={(swiper) => console.log(swiper)}
    >
      {songPage.map((data, index) => (
        <p key={index}>{data.length}</p>
      ))}
      안녕하세요
    </Swiper>
  );
};

const StyledUl = styled.ul`
  margin: 0;
  padding: 0;
`;

export default SongSildeList;
