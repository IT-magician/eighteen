import React, { useEffect, useRef, useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import styled from "styled-components";
import { searchForTitle } from "../../../apis/search";
import { searchState } from "../../../recoil/atom/searchState";
import { Song, SongItem } from "../../common/song";
import SongResultDefault from "./SongResultDefault";
import SongResultEmpty from "./SongResultEmpty";
import SongResultLoading from "./SongResultLoading";

const SongResultList = (): JSX.Element => {
  const [list, setList] = useState<Song[]>([]);
  const [search, setSearch] = useRecoilState(searchState);

  useEffect(() => {
    if (search.loading || !search.keyword) return;
    setSearch({ ...search, loading: true });

    // 1초마다 한번씩 최종 변경된 사항으로 검색합니다
    setTimeout(() => {
      setSearch((pre) => {
        const setData = async () => {
          const { data } = await searchForTitle(pre.keyword);
          console.dir(data);
        };
        setData();
        return { ...pre, loading: false };
      });
    }, 1000);
  }, [search.keyword, search.type]);

  return (
    <StyledDiv>
      <h2>검색 결과</h2>
      {search.loading && <SongResultLoading />}
      {search.loading || Boolean(list.length) || Boolean(search.keyword) || <SongResultDefault />}
      {search.loading || Boolean(list.length) || (Boolean(search.keyword) && <SongResultEmpty />)}
      <ul>
        {list.map((item, index) => (
          <SongItem
            key={index}
            musicId={item.musicId}
            title={item.title}
            singer={item.singer}
            isEighteen={item.isEighteen}
            thumbnailUrl={item.thumbnailUrl}
          />
        ))}
      </ul>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & > h2 {
    font-weight: 400;
    margin-top: 40px;
  }
  & > ul {
    margin: 0;
    padding: 0;
    /* height: 80px; */
    display: flex;
    flex-direction: column;

    & > *:nth-child(n) {
      margin-top: 4px;
      animation: identifier 1s;
    }
    & > *:nth-child(5n) {
      /* margin-bottom: 32px; */
    }
  }
  @keyframes identifier {
    from {
      opacity: 0;
      margin-top: -8px;
    }
    to {
      opacity: 1;
      margin-top: 4px;
    }
  }
`;

export default SongResultList;
