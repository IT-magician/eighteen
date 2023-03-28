import React from "react";
import styled from "styled-components";

const SongResultEmpty = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <img src={`${process.env.PUBLIC_URL}/icon/empty.png`} />
        <p>
          이런!
          <br />
          해당하는 노래가 없어요
        </p>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & > div {
    top: 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    line-height: 24px;
    & img {
      width: 40%;
    }
    & p {
      margin: 0;
    }
  }
`;

export default SongResultEmpty;
