import React from "react";
import styled from "styled-components";
import { TbHome, TbMusic, TbSearch, TbUser } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

export type LinkType = "home" | "favorite" | "song" | "mypage";

interface Props {
  link: LinkType;
}

const NavItem = ({ link }: Props): JSX.Element => {
  const navigate = useNavigate();

  // 지정한 아이콘 출력
  const icon = () => {
    switch (link) {
      case "home":
        return <TbHome />;
      case "favorite":
        return <TbMusic />;
      case "song":
        return <TbSearch />;
      case "mypage":
        return <TbUser />;
    }
  };

  // 페이지 이동 함수
  const onClick = () => {
    navigate(link);
  };
  return <StyledButton onClick={onClick}>{icon()}</StyledButton>;
};

const StyledButton = styled.button`
  width: 64px;
  height: 64px;
  border: 0;
  font-size: 24px;
  color: var(--black-50);
  background: none;
  z-index: 1;
  cursor: pointer;
`;

export default NavItem;
