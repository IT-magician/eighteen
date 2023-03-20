import React, { useMemo } from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
import NavItem, { LinkType } from "./NavItem";

/**
 * 하단 네비게이션 바
 */
const NavBar = () => {
  // 출력할 nav 버튼 목록
  const navList: LinkType[] = ["home", "favorite", "song", "mypage"];

  // 현재 pointer 애니메이션을 주기 위해 필요합니다.
  const location = useLocation();
  const locationIdx = useMemo(() => {
    const idx = navList.findIndex((item) => item === location.pathname.slice(1));
    return idx;
  }, [location]);

  return (
    <StyledDiv location={locationIdx}>
      <div>
        {locationIdx < 0 || <div className="pointer" />}
        {navList.map((link) => (
          <NavItem key={link} link={link} />
        ))}
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div<{ location: number }>`
  width: 100%;
  height: 80px;
  position: absolute;
  bottom: 0;
  left: 0;
  box-shadow: var(--shadow);
  background-color: var(--black-500);
  display: flex;
  justify-content: center;

  & > div {
    position: relative;
    width: 100%;
    max-width: 800px;
    height: 100%;
    margin: auto 16px;
    display: flex;
    align-items: center;
    justify-content: space-evenly;
  }
  & .pointer {
    width: 64px;
    height: 64px;
    border-radius: 32px;
    position: absolute;
    transition: all 0.25s ease;
    left: ${({ location }) => `calc(64px * ${location} + (100% - 64px * 4) / 5 * ${location + 1})`};
    z-index: 0;
    background-color: var(--black-400);
    animation: none;
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
  }
  &:active .pointer {
    border-radius: 30px;
    -webkit-transform: scale3d(1.2, 0.8, 0.5);
    transform: scale3d(1.2, 0.8, 0.5);
  }
`;

export default NavBar;
