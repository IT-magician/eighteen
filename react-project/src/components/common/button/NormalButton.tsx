import React from "react";
import styled from "styled-components";

interface Props {
  text: string;
  color: string;
  onClick: () => void;
}

/**
 * 기본 텍스트 버튼 컴포넌트
 */
const NormalButton = ({ text, color, onClick }: Props): JSX.Element => {
  return (
    <StyledButton color={color} onClick={onClick}>
      {text}
    </StyledButton>
  );
};

const StyledButton = styled.button<{ color: string }>``;

export default NormalButton;
