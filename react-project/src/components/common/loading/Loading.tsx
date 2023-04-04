import React from "react";
import styled from "styled-components";

const Loading = (): JSX.Element => {
  return (
    <StyledDiv>
      <div />
      <div />
      <div />
      <div />
      <span>LOADING · · ·</span>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  position: relative;
  margin: auto;

  & > span {
    position: absolute;
    top: 56px;
    letter-spacing: 0;
  }

  & > div {
    width: 32px;
    height: 32px;
    background-color: var(--blue-500);
    border-radius: 20px;
    box-shadow: var(--shadow);
    margin: 4px;
    -webkit-animation: jello-vertical 1.2s ease-in-out 0s infinite;
    animation: jello-vertical 1.2s ease-in-out 0s infinite;

    &:nth-child(2) {
      -webkit-animation-delay: 0.2s;
      animation-delay: 0.2s;
      /* background-color: #7211db; */
    }
    &:nth-child(3) {
      -webkit-animation-delay: 0.4s;
      animation-delay: 0.4s;
      /* background-color: #c75ee4; */
    }
    &:nth-child(4) {
      -webkit-animation-delay: 0.4s;
      animation-delay: 0.6s;
      /* background-color: #0056fe; */
    }
  }

  @-webkit-keyframes jello-vertical {
    65% {
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
      background-color: #7211db;
      margin-top: 4px;
    }
    70% {
      -webkit-transform: scale3d(0.75, 1.25, 1);
      transform: scale3d(0.75, 1.25, 1);
    }
    75% {
      -webkit-transform: scale3d(1.25, 0.75, 1);
      transform: scale3d(1.25, 0.75, 1);
    }
    85% {
      -webkit-transform: scale3d(0.85, 1.15, 1);
      transform: scale3d(0.85, 1.15, 1);
      background-color: var(--blue-200);
      margin-top: -80px;
    }
    90% {
      -webkit-transform: scale3d(1.05, 0.95, 1);
      transform: scale3d(1.05, 0.95, 1);
    }
    95% {
      -webkit-transform: scale3d(0.95, 1.05, 1);
      transform: scale3d(0.95, 1.05, 1);
    }
    100% {
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
    }
  }
  @keyframes jello-vertical {
    65% {
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
      background-color: #7211db;
      margin-top: 4px;
    }
    70% {
      -webkit-transform: scale3d(0.75, 1.25, 1);
      transform: scale3d(0.75, 1.25, 1);
    }
    75% {
      -webkit-transform: scale3d(1.25, 0.75, 1);
      transform: scale3d(1.25, 0.75, 1);
    }
    85% {
      -webkit-transform: scale3d(0.85, 1.15, 1);
      transform: scale3d(0.85, 1.15, 1);
      background-color: var(--blue-200);
      margin-top: -80px;
    }
    90% {
      -webkit-transform: scale3d(1.05, 0.95, 1);
      transform: scale3d(1.05, 0.95, 1);
    }
    95% {
      -webkit-transform: scale3d(0.95, 1.05, 1);
      transform: scale3d(0.95, 1.05, 1);
    }
    100% {
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
    }
  }
`;

export default Loading;
