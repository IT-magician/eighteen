import React, { useEffect, useState } from "react";
import { TbChevronDown } from "react-icons/tb";
import styled from "styled-components";

export interface Option<T> {
  text: string;
  value: T;
}

interface Props<T> {
  defaultIdx?: number;
  placeholder?: string;
  options: Option<T>[];
  setValue(value: T): void;
}

function Select<T>({ defaultIdx = 0, placeholder = "", options, setValue }: Props<T>): JSX.Element {
  const [valueIdx, setValueIdx] = useState<number>(defaultIdx);

  const onClick = (e: React.MouseEvent<HTMLLIElement>) => {
    if (e.target instanceof HTMLLIElement) {
      const newValueIndex = e.target.value;
      setValueIdx(newValueIndex);
      setValue(options[newValueIndex].value);
    }
  };

  return (
    <StyledDiv tabIndex={0}>
      <div className="select">
        {options[valueIdx].text || placeholder}
        <TbChevronDown />
      </div>
      <ul>
        {options.map((item, index) => (
          <li key={index} value={index} className={index === valueIdx ? "check" : undefined} onClick={onClick}>
            {item.text}
          </li>
        ))}
      </ul>
    </StyledDiv>
  );
}

const StyledDiv = styled.div`
  width: max-content;
  height: max-content;
  max-height: 40px;
  overflow: hidden;
  transition: max-height 0.2s;
  cursor: pointer;

  &:focus {
    max-height: 200px;
    outline: 0;

    & > .select {
      outline: 0;
      border-bottom-width: 4px;
      font-weight: 700;
      & > svg {
        transform: rotate(180deg);
      }
    }

    & > ul {
      height: fit-content;
      max-height: 160px;
      overflow: auto;
    }
  }

  & > .select {
    border: 0;
    border-bottom: 2px solid var(--black-50);
    padding: 0 8px 0 16px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    min-width: max-content;
    max-width: 240px;
    height: 40px;
    box-sizing: border-box;
    font: inherit;
    font-weight: 400;
    font-size: 16px;
    color: var(--black-50);
    background-color: var(--black-opacity);
    & > svg {
      font-size: 24px;
      margin-left: 8px;
      transition: all 0.2s;
    }
  }
  & > ul {
    margin: 0;
    padding: 0;
    background-color: var(--black-opacity);
    // 스크롤 디자인 CSS
    &::-webkit-scrollbar {
      width: 4px;
    }
    &::-webkit-scrollbar-thumb {
      background-color: var(--blue-700);
      width: 4px;
      border-radius: 2px;
    }
    &::-webkit-scrollbar-track {
      background: none;
    }
    & > li {
      list-style: none;
      margin: 0;
      padding: 8px 8px 8px 16px;
      &:hover {
        background-color: var(--blue-800);
      }
      &.check {
        background-color: var(--blue-500);
      }
    }
  }
`;

export default Select;
