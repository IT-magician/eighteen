import React from "react";
import styled from "styled-components";

interface Props {
  value: string;
  setValue(value: string): void; // 값 변경 메소드
  focusOut?(value: string): void; // 포커스아웃 시 최종 value값을 반환하는 메소드
}

/**
 * 기본 Input 컴포넌트
 */
const Input = ({ value, setValue, focusOut }: Props): JSX.Element => {
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };
  const onFocusOut = (e: React.FocusEvent<HTMLInputElement>) => {
    if (focusOut) focusOut(e.target.value);
  };

  return <StyledInput value={value} onChange={onChange} onBlur={onFocusOut} />;
};

const StyledInput = styled.input`
  background: none;
  border: 0;
  border-bottom: 2px solid var(--black-50);
  width: 100%;
  max-width: 320px;
  height: 40px;
  box-sizing: border-box;
  line-height: 40px;
  font: inherit;
  font-size: 16px;
  color: var(--black-50);
  padding: 16px;
  background-color: var(--black-opacity);

  &:focus {
    outline: 0;
    border-bottom-width: 4px;
    font-weight: 700;
  }
`;

export default Input;
