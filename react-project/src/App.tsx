import React from "react";
import { Route, Routes } from "react-router";
import styled from "styled-components";
import { Logo } from "./components/common/logo";
import { NavBar } from "./components/common/nav";
import { Favorite, Home, Login, Mypage, Setting, Recommend, Song, SongDetail } from "./pages";

const App = (): JSX.Element => {
  return (
    <StyledDiv className="App">
      <div className="logo">
        <Logo />
      </div>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/recommend/:type" element={<Recommend />} />
        <Route path="/song" element={<Song />} />
        <Route path="/song/:songid" element={<SongDetail />} />
        <Route path="/favorite" element={<Favorite />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/setting" element={<Setting />} />
      </Routes>
      <NavBar />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  min-height: 100vh;

  & > .logo {
    position: absolute;
    margin-left: 50%;
    padding: 56px 32px;
    transform: translateX(-50%);
    display: flex;
    justify-content: end;
    width: 100%;
    max-width: 1080px;
    box-sizing: border-box;
  }
  // 그라데이션 효과
  &::before {
    z-index: 0;
    content: "";
    position: fixed;
    top: -5vh;
    left: 0;
    width: 100%;
    height: 30vh;
    background: var(--gradation);
    filter: blur(80px);
    opacity: 0.75;
  }
  // 실제 페이지 정보가 담길 페이지 컴포넌트
  & > *:nth-child(2) {
    position: relative;
    overflow: auto;
    z-index: 1;
    max-height: calc(100vh - 80px);

    // 스크롤 디자인 CSS
    &::-webkit-scrollbar {
      width: 8px;
    }
    &::-webkit-scrollbar-thumb {
      background-color: var(--blue-700);
      width: 8px;
      border-radius: 8px;
    }
    &::-webkit-scrollbar-track {
      background-color: #00000080;
    }
  }
  // navbar
  & > *:nth-child(3) {
    position: fixed;
  }
`;

export default App;
