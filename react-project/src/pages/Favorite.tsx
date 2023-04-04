import React from "react";
import styled from "styled-components";
import { FavoriteRandomChoice, FavoriteSongResult, FavoriteSongSearch } from "../components/Favorite";

/**
 * 애창곡
 */
const Favorite = (): JSX.Element => {
  return (
    <StyledDiv>
      <h1>애창곡 관리</h1>
      <FavoriteRandomChoice />
      <FavoriteSongSearch />
      <FavoriteSongResult />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  overflow-x: clip;
  padding: 96px 16px 80px;
  & > h1 {
    font-weight: 400;
  }
`;

export default Favorite;
