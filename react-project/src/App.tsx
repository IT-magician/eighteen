import axios from "axios";
import React, { useEffect } from "react";
import { Route, Routes } from "react-router";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { getMainData } from "./apis/main";
import { checkUser } from "./apis/oauth";
import { Logo } from "./components/common/logo";
import { NavBar } from "./components/common/nav";
import { Favorite, Home, Login, Mypage, Recommend, Song, SongDetail, Setting } from "./pages";
import { userState } from "./recoil/atom";

const App = (): JSX.Element => {
  const [user, setUser] = useRecoilState(userState);
  useEffect(() => {
    // 최초 접근 시 main 요청을 통해 사용자 정보를 가져옵니다.
    const getAccessToken = async () => {
      try {
        // 최초 접근 시 ACCESS TOKEN 발급을 위해 요청을 보냅니다
        await checkUser();
        // CASE 1-1 : 200 ACCEPTED
        // 이 경우 발급받은 ACCESS TOKEN을 활용한 요청을 위해 callback 함수를 호출합니다
        enterService();
      } catch (e) {
        // 요청 실패
        if (axios.isAxiosError(e)) {
          if (e.response?.status === 401) {
            // CASE 1-2 : 401 UNAUTHORIZED
            // 이 경우 재 로그인이 필요하므로 로그인 화면으로 이동합니다
            setUser(null);
          }
        }
      }
    };

    const enterService = async () => {
      try {
        const response = await getMainData();
        if (response.status === 204) {
          // CASE 2 : 204 NO_CONTENT
          // 이 경우 등록된 회원이나 정상적인 회원가입을 거치지 않은 유저입니다
          // 따라서 회원가입 화면으로 이동합니다
          setUser({
            nickname: "",
            birth: "",
            gender: "M",
            profileImage: "",
          });
        } else if (response.status === 200) {
          // CASE 3 : 200 ACCEPTED
          // 이 경우 정상적으로 등록된 유저이으로 서비스 화면으로 이동합니다
          // TODO: response data를 토대로 user update
          setUser(response.data);
        }
      } catch (e) {
        if (axios.isAxiosError(e)) {
          if (e.response?.status === 401) {
            // CASE 1 : 401 UNAUTHORIZED
            // 이 경우 토큰이 만료되었거나, 로그인하지 않은 유저입니다
            // 최초의 경우 REFLESH TOKEN을 토대로 다시 한 번 ACCESS TOKEN을 발급합니다
            getAccessToken();
          }
        }
      }
    };

    getAccessToken();
  }, []);

  if (!user) {
    return (
      <StyledDiv className="App">
        <div className="Page" id="Page">
          <div className="logo">
            <Logo />
          </div>
          <Login />
        </div>
      </StyledDiv>
    );
  } else {
    return (
      <StyledDiv className="App">
        <div className="Page" id="Page">
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
  }
};

const StyledDiv = styled.div`
  height: 100vh;

  // 실제 페이지 정보가 담길 페이지 컴포넌트
  & > .Page {
    position: relative;
    width: 100%;
    height: calc(100vh - 80px);
    box-sizing: border-box;
    overflow: auto;
    display: flex;
    flex-direction: column;
    align-items: center;

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
      display: flex;
      justify-content: flex-end;
      & > div {
        padding: 56px 32px;
        box-sizing: border-box;
      }
    }
    & > * {
      box-sizing: border-box;
      width: 100%;
      max-width: 800px;
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
