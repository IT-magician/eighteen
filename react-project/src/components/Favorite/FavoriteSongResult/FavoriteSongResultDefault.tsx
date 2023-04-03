import React from "react";
import styled from "styled-components";

const SongResultDefault = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <img src={`${process.env.PUBLIC_URL}/icon/favorite.png`} />
        <p>
          아직 등록된 애창곡이 없어요 <br />
          나만의 애창곡을 찾으러 가볼까요?
        </p>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  & > div {
    margin: 40px 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    line-height: 24px;
    & img {
      width: 40%;
      -webkit-animation: jello-horizontal 0.9s both;
      animation: jello-horizontal 0.9s both;
    }
    & p {
      margin: 0;
    }
  }
`;

export default SongResultDefault;
