import React from "react";
import { TbCircleCheck, TbCircleX, TbInfoCircle } from "react-icons/tb";
import styled from "styled-components";
import { VerifyStatus } from "./type";

interface Props {
  desc: string;
  status: VerifyStatus;
}

/**
 * 조건 출력 아이템 컴포넌트
 */
const VerifyItem = ({ status, desc }: Props) => {
  return (
    <StyledLi status={status}>
      {status === "default" ? (
        <TbInfoCircle />
      ) : status === "fail" ? (
        <TbCircleX />
      ) : status === "pass" ? (
        <TbCircleCheck />
      ) : null}
      {desc}
    </StyledLi>
  );
};

const StyledLi = styled.li<{ status: VerifyStatus }>`
  list-style: none;
  margin-top: 8px;
  font-size: 12px;
  display: flex;

  ${({ status }) => {
    switch (status) {
      case "default":
        return "color: var(--black-50);";
      case "fail":
        return "color: var(--pink-500); font-weight: 700;";
      case "pass":
        return "color: var(--blue-300); font-weight: 700;";
      case "loading":
        return "color: var(--black-200);";
    }
  }}

  & > svg {
    font-size: 16px;
    margin-top: -1px;
    margin-right: 2px;
  }
`;

export default VerifyItem;
