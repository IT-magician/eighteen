import React from "react";
import styled from "styled-components";

interface Props {
  text: string;
  active?: boolean;
  onClick: () => void;
}

/**
 * 기본 텍스트 버튼 컴포넌트
 */
const ToggleTextButton = ({ text, active = false, onClick }: Props): JSX.Element => {
  return (
    <StyledButton active={active} onClick={onClick}>
      {text}
    </StyledButton>
  );
};

const StyledButton = styled.button<{ active: boolean }>`
  font: inherit;
  color: var(--black-50);
  padding: 8px 16px;
  border-radius: 24px;
  border: 0;
  font-weight: ${({ active }) => (active ? 700 : 400)};
  background-color: ${({ active }) => (active ? "var(--blue-500)" : "var(--black-500)")};
  box-shadow: var(--shadow);

  &:hover {
    background-color: ${({ active }) => (active ? "var(--blue-400)" : "var(--black-400)")};
  }

  &:active {
    box-shadow: none;
  }
`;

export default ToggleTextButton;
