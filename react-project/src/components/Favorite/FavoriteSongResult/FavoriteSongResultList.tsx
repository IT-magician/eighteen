import axios from "axios";
import React, { useEffect, useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import styled from "styled-components";
import { getEighteenList } from "../../../apis/myEighteen";
import { authState } from "../../../recoil/atom/authState";
import { searchState } from "../../../recoil/atom/searchState";
import { Song, SongItem } from "../../common/song";
import FavoriteSongResultDefault from "./FavoriteSongResultDefault";
import FavoriteSongResultEmpty from "./FavoriteSongResultEmpty";
import FavoriteSongResultLoading from "./FavoriteSongResultLoading";

const FavoriteSongList = () => {
  const [auth, setAuth] = useRecoilState(authState);
  const [list, setList] = useState<Song[]>([]);
  const [page] = useState<number>(0);
  const search = useRecoilValue(searchState);

  useEffect(() => {
    if (search.loading) return;
    const getList = async () => {
      try {
        const res = await getEighteenList(page, 10, auth.token);
        if (res.status === 204) {
          // CASE 1: 204 NO CONTENT
          setList([]);
        } else if (res.status === 200) {
          // CASE 2: 200 ACCEPTED
          setList(res.data.content);
        }
      } catch (e) {
        if (axios.isAxiosError(e)) {
          if (e.response?.status === 401) {
            // CASE 3: 401 UNAUTHORIZED
            setAuth({ ...auth, token: "" });
          }
        }
      }
    };
    getList();
  }, [search.loading]);

  return (
    <StyledDiv>
      {search.loading && <FavoriteSongResultLoading />}
      {search.loading || Boolean(list.length) || Boolean(search.keyword) || <FavoriteSongResultDefault />}
      {search.loading || Boolean(list.length) || (Boolean(search.keyword) && <FavoriteSongResultEmpty />)}
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

export default FavoriteSongList;
