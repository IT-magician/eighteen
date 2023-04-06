import React from "react";
import styled from "styled-components";

type Colortype = "blue" | "red" | "gradation";

interface Props {
  text: string;
  color: Colortype;
  onClick: () => void;
}

/**
 * 기본 텍스트 버튼 컴포넌트
 */
const TextButton = ({ text, color, onClick }: Props): JSX.Element => {
  return (
    <StyledButton color={color} onClick={onClick}>
      {text}
    </StyledButton>
  );
};

const StyledButton = styled.button<{ color: Colortype }>`
  font: inherit;
  font-size: 20px;
  font-weight: 900;
  color: var(--black-50);
  padding: 16px 24px;
  border: 0;
  border-radius: 16px;
  background: none;
  position: relative;
  overflow: hidden;

  &::before {
    content: "";
    width: 200%;
    height: 200%;
    z-index: -1;
    position: absolute;
    top: -50%;
    left: -50%;
    ${({ color }) => {
      switch (color) {
        case "red":
          return "background-color: var(--pink-500)";
        case "gradation":
          return "background: var(--gradation); filter: blur(4px);";
        case "blue":
        default:
          return "background-color: var(--blue-500)";
      }
    }};
  }
  box-shadow: var(--shadow);

  &:hover {
    &::before {
      ${({ color }) => {
        switch (color) {
          case "red":
            return "background-color: var(--pink-400)";
          case "gradation":
            return "background: var(--gradation); filter: blur(4px);";
          case "blue":
          default:
            return "background-color: var(--blue-400)";
        }
      }};
    }
  }

  &:active {
    &::before {
      ${({ color }) => {
        switch (color) {
          case "red":
            return "background-color: var(--pink-600)";
          case "gradation":
            return "background: var(--gradation); filter: blur(4px);";
          case "blue":
          default:
            return "background-color: var(--blue-600)";
        }
      }};
    }
    box-shadow: none;
  }
`;

export default TextButton;
