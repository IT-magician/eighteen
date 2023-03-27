import React, { useState } from "react";
import { useRecoilValue } from "recoil";
import styled from "styled-components";
import { searchState } from "../../../recoil/atom/searchState";
import { Song, SongItem } from "../../common/song";
import SongResultDefault from "./SongResultDefault";
import SongResultEmpty from "./SongResultEmpty";
import SongResultLoading from "./SongResultLoading";

const SongResultList = (): JSX.Element => {
  const [list, setList] = useState<Song[]>([]);
  const search = useRecoilValue(searchState);

  return (
    <StyledDiv>
      <h2>검색 결과</h2>
      {search.loading && <SongResultLoading />}
      {search.loading || list.length || Boolean(search.keyword) || <SongResultDefault />}
      {search.loading || list.length || (search.keyword && <SongResultEmpty />)}
      {list.map((item, index) => (
        <SongItem key={index} musicId={item.musicId} title={item.title} singer={item.singer} isEighteen={false} />
      ))}
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & > h2 {
    font-weight: 400;
    margin-top: 40px;
  }
`;

export default SongResultList;
