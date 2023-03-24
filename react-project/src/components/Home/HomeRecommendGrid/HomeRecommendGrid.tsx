import React from "react";
import styled from "styled-components";

const HomeRecommendGrid = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <button>
          <span>취향맞춤 추천</span>
          <h2>
            너도 몰랐던
            <br />
            너의 에이틴
          </h2>
        </button>

        <button>
          <span>날씨 기반 추천</span>
          <h2>
            오늘 같은
            <br />
            날씨엔
          </h2>
        </button>
      </div>
      <div>
        <button>
          <span>다들 뭐부를까?</span>
          <h2>
            성별·나이별
            <br />
            애창곡 랭킹
          </h2>
        </button>
      </div>
      <div>
        <button>
          <span>감정 기반 추천</span>
          <h2>
            지금 너의
            <br />
            기분은?
          </h2>
        </button>
        <button>
          <span>상황 기반 추천</span>
          <h2>회식도 모임도 자신있게!</h2>
        </button>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  & div {
    width: 100%;
    display: flex;
    flex: 1 1 0;
    margin-bottom: 2px;
  }
  & button {
    border: 0;
    border-radius: 16px;
    margin: 4px;
    padding: 16px;
    width: 100%;
    height: 160px;
    font: inherit;
    display: flex;
    justify-content: end;
    flex-direction: column;
    text-align: left;
    color: var(--black-50);
    background-color: var(--blue-500);
    box-shadow: var(--shadow);
  }
  & h2 {
    font-size: 24px;
    font-weight: 900;
    word-break: keep-all;
    margin: 0;
  }
`;

export default HomeRecommendGrid;
