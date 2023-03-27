import React from "react";
import { TbArrowNarrowLeft } from "react-icons/tb";
import { useNavigate } from "react-router";
import styled from "styled-components";

/**
 * 뒤로가기 버튼
 */
const BackButton = (): JSX.Element => {
  const navigate = useNavigate();
  const onClick = () => {
    navigate(-1);
  };

  return (
    <StyledButton onClick={onClick}>
      <TbArrowNarrowLeft />
    </StyledButton>
  );
};

const StyledButton = styled.button`
  color: var(--black-50);
  font-size: 44px;
  display: flex;
  background: none;
  border: 0;
  & svg {
    stroke-width: 2px;
  }
`;

export default BackButton;
