import React from "react";
import styled from "styled-components";

// 버튼 아이콘 타입
type IconType = "favorite" | "youtube" | "modify" | "save" | "setting";

interface Props {
  type: IconType;
  active?: boolean;
  onClick: () => void;
}

/**
 * 아이콘 버튼 컴포넌트
 */
const IconButton = ({ type, onClick }: Props): JSX.Element => {
  return <StyledButton onClick={onClick}></StyledButton>;
};

const StyledButton = styled.button``;

export default IconButton;
