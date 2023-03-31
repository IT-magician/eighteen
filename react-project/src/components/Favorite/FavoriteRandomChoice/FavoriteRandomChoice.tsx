import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Swiper, SwiperSlide } from "swiper/react";
import { getEighteenRandom } from "../../../apis/myEighteen";
import { Song } from "../../common/song";

const FavoriteRandomChoice = () => {
  const [songList, setSongList] = useState<Song[]>([]);

  useEffect(() => {
    const request = async () => {
      const { data } = await getEighteenRandom();
      setSongList(data.randoms);
      console.log(data.randoms);
    };
    request();
  }, []);

  if (songList.length)
    return (
      <StyledDiv>
        <h2>랜덤 선곡</h2>
        <Swiper spaceBetween={16} slidesPerView={"auto"}>
          {songList.map((item, index) => (
            <SwiperSlide key={index} className="choice-song">
              <img src={item.thumbnailUrl} />
              <div className="music-id">{item.musicId}</div>
              <div className="title">{item.title}</div>
            </SwiperSlide>
          ))}
        </Swiper>
      </StyledDiv>
    );
  else return <></>;
};

const StyledDiv = styled.div`
  & > h2 {
    font-weight: 400;
    margin: 32px 0;
  }
  .swiper {
    max-width: 100%;
    overflow: visible;
  }
  & .choice-song {
    box-sizing: border-box;
    width: 144px;
    height: 144px;
    overflow: hidden;
    position: relative;
    padding: 8px 16px;
    display: flex;
    flex-direction: column;
    justify-content: end;
    box-shadow: var(--shadow);
    & > img {
      width: 100%;
      top: 0;
      left: 0;
      position: absolute;
      background-color: var(--black-800);
      filter: brightness(50%);
      z-index: -1;
    }
    & > .music-id {
      width: fit-content;
      border-radius: 20px;
      padding: 8px 16px;
      font-size: 14px;
      font-weight: 900;
      letter-spacing: 0;
      display: block;
      background-color: var(--blue-500);
    }

    & > .title {
      font-size: 16px;
      font-weight: 900;
      margin: 4px;
    }
  }
`;

export default FavoriteRandomChoice;
