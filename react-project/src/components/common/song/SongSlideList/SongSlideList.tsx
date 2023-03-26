import React, { useMemo } from "react";
import styled from "styled-components";
import { Swiper, SwiperSlide } from "swiper/react";
import { Song, SongItem } from "../SongItem";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination } from "swiper";

interface Props {
  songList: Song[];
  size?: number;
}

const SongSlideList = ({ songList, size = 5 }: Props): JSX.Element => {
  // 페이지 사이즈만큼 곡 목록을 그룹핑합니다
  const songPage = useMemo(() => {
    let page: Song[] = [];

    const pageList = songList.reduce((list: Song[][], song, index) => {
      // 첫번째 원소가 아니면서 size로 나누어 떨어지는 index는 새 페이지로 설정하여 더합니다
      if (index && !(index % size)) {
        list.push([...page]);
        page = [];
      }
      page.push(song);
      return list;
    }, []);

    // 마지막 page에 유효한 값이 있는 경우 해당 페이지도 추가합니다
    if (page.length) pageList.push([...page]);
    return pageList;
  }, [songList]);

  return (
    <StyledDiv size={size}>
      <Swiper
        modules={[Pagination]}
        spaceBetween={50}
        slidesPerView={1}
        pagination={{ clickable: true }}
        onSlideChange={() => console.log("slide change")}
        onSwiper={(swiper) => console.log(swiper)}
      >
        {songPage.map((page, index) => (
          <SwiperSlide key={`page-${index}`} className="swiper-slide">
            <ul>
              {page.map((song, index) => (
                <SongItem
                  key={index}
                  musicId={song.musicId}
                  title={song.title}
                  singer={song.singer}
                  isEighteen={song.isEighteen}
                />
              ))}
            </ul>
          </SwiperSlide>
        ))}
      </Swiper>
    </StyledDiv>
  );
};

const StyledDiv = styled.div<{ size: number }>`
  & .swiper-slide {
    height: ${({ size }) => `${size * 68 + 8}px`};
  }
  & ul {
    margin: 0;
    padding: 0;
  }

  .swiper-pagination {
    position: relative;
    margin-top: 16px;
    height: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .swiper-pagination-bullet {
    background-color: var(--black-400);
    opacity: 1;
    width: 12px;
    height: 12px;
    transition: all 0.3s;
  }
  .swiper-pagination-bullet-active {
    background-color: var(--blue-500);
    width: 16px;
    height: 16px;
  }
`;

export default SongSlideList;
