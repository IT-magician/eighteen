import React from "react";
import styled from "styled-components";
import SongSearchInput from "./SongSearchInput";

const SongSearchComponent = (): JSX.Element => {
  return (
    <StyledDiv>
      <SongSearchInput />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default SongSearchComponent;
