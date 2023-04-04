import React, { forwardRef } from "react";
import styled from "styled-components";

interface Props {
  value?: string;
  onClick?(): void;
}

const RegusterProfileDataPicker = forwardRef<HTMLButtonElement, Props>(({ value, onClick }: Props, ref) => {
  return (
    <StyledButton onClick={onClick} ref={ref}>
      {value}
    </StyledButton>
  );
});

RegusterProfileDataPicker.displayName = "RegusterProfileDataPicker";

const StyledButton = styled.button`
  height: 40px;
  box-sizing: border-box;
  font: inherit;
  color: var(--black-50);
  background-color: var(--black-opacity);
  border: 0;
  border-bottom: 2px solid var(--black-50);
  padding: 0 16px;

  &:focus {
    font-weight: 700;
    border-bottom: 4px solid var(--black-50);
  }
`;

export default RegusterProfileDataPicker;
