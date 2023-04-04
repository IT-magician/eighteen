import React from "react";
import styled from "styled-components";
import {
  TbStar,
  TbStarFilled,
  TbBrandYoutube,
  TbPencil,
  TbDeviceFloppy,
  TbSettings,
  TbQuestionMark,
} from "react-icons/tb";

// 버튼 아이콘 타입
type IconType = "favorite" | "youtube" | "modify" | "save" | "setting";

interface Props {
  type: IconType; // 아이콘 타입
  active?: boolean; // 아이콘 활성화 여부(토글 시)
  onClick: () => void; // 클릭 이벤트
}

/**
 * 아이콘 버튼 컴포넌트
 */
const IconButton = ({ type, active = false, onClick }: Props): JSX.Element => {
  // type별 지정한 아이콘 반환
  const Icon = () => {
    switch (type) {
      case "favorite":
        if (active) return <TbStarFilled />;
        return <TbStar />;
      case "modify":
        return <TbPencil />;
      case "save":
        return <TbDeviceFloppy />;
      case "setting":
        return <TbSettings />;
      case "youtube":
        return <TbBrandYoutube />;
      default:
        return <TbQuestionMark />;
    }
  };

  return (
    <StyledButton active={active} onClick={onClick}>
      <div className="background" />
      {Icon()}
    </StyledButton>
  );
};

const StyledButton = styled.button<{ active: boolean }>`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48px;
  height: 48px;
  border: 0;
  background: none;

  & > svg {
    z-index: 1;
    font-size: 20px;
    color: ${({ active }) => (active ? "var(--blue-500)" : "var(--black-50)")};
    stroke-width: 2px;
  }
  & > .background {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 24px;
    opacity: 0.5;
    background-color: var(--black-900);
  }
  &:hover > .background,
  &:active > .background {
    background-color: var(--black-400);
  }
`;

export default IconButton;
