import React from "react";
import { Route, Routes } from "react-router";
import styled from "styled-components";
import { NavBar } from "./components/common/navigate";
import { Favorite, Home, Login, Mypage, Recommend, Song, SongDetail } from "./pages";

const App = (): JSX.Element => {
  return (
    <StyledDiv className="App">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/recommend/:type" element={<Recommend />} />
        <Route path="/song" element={<Song />} />
        <Route path="/song/:songid" element={<SongDetail />} />
        <Route path="/favorite" element={<Favorite />} />
        <Route path="/mypage" element={<Mypage />} />
      </Routes>
      <NavBar />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  min-height: 100vh;
`;

export default App;
