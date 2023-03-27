import React, { useEffect } from "react";
import { Route, Routes } from "react-router";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { Logo } from "./components/common/logo";
import { NavBar } from "./components/common/nav";
import { Favorite, Home, Login, Mypage, Recommend, Song, SongDetail, Setting } from "./pages";
import { userState } from "./recoil/atom";

const App = (): JSX.Element => {
  const [user, setUser] = useRecoilState(userState);
  useEffect(() => {
    // 유저 정보가 없는 경우 로그인을 통해 해당 정보를 가져옵니다
    if (!user) {
      // TODO: access Token 요청 실행
      setUser({
        userid: 0,
        birth: "1999-03-23",
        gender: 0,
        nickname: "봉명동퉁퉁이",
        email: "test@gamil.com",
        profileImage: `${process.env.public_url}/user/undefined.png`,
      });
      // TODO: 실패시 reflesh Token 재발급을 위한 로그인 페이지로 이동
    }
  }, [user]);

  return (
    <StyledDiv className="App">
      <div className="Page">
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
      </div>
      <NavBar />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  height: 100vh;
  max-width: 800px;
  margin: auto;

  // 실제 페이지 정보가 담길 페이지 컴포넌트
  & > .Page {
    position: relative;
    height: calc(100vh - 80px);
    box-sizing: border-box;
    overflow: auto;

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
    & > .logo {
      overflow: visible;
      height: 0;
      & > div {
        margin-left: 50%;
        padding: 56px 32px;
        transform: translateX(-50%);
        justify-content: end;
        width: 100%;
        max-width: 1080px;
        box-sizing: border-box;
      }
    }
  }

  // 그라데이션 효과
  &::before {
    content: "";
    position: absolute;
    z-index: 0;
    top: -60px;
    left: 0;
    width: 100%;
    height: 240px;
    background: var(--gradation);
    filter: blur(80px);
    opacity: 0.75;
  }

  // navbar
  & > *:last-child {
    position: fixed;
  }
`;

export default App;
