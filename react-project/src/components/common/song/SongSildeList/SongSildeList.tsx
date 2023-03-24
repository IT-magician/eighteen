import React from "react";
import styled from "styled-components";
import { Song } from "../SongItem";

interface Props {
  musicList: Song[];
}

const SongSildeList = ({ musicList }: Props): JSX.Element => {
  return <StyledUl></StyledUl>;
};

const StyledUl = styled.ul``;

export default SongSildeList;
