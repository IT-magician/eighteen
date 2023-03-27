import React, { useState, useEffect } from "react";
import { useRecoilValue } from "recoil";
import { userState } from "../recoil/atom";
import styled from "styled-components";
import moment from "moment";
import { Profile } from "../components/mypage/profile";
import { SongHistory } from "../components/mypage/songHistory";

interface Music {
  isEighteen: boolean;
  musicId: number;
  singer: string;
  thumbnailUrl: string;
  title: string;
}

const MUSICLIST: Music[] = [
  {
    isEighteen: true,
    musicId: 1,
    singer: "윤하",
    thumbnailUrl: "string",
    title: "사건의 지평선",
  },
  {
    isEighteen: false,
    musicId: 2,
    singer: "윤하",
    thumbnailUrl: "string",
    title: "오르트구름",
  },
  {
    isEighteen: true,
    musicId: 3,
    singer: "윤하",
    thumbnailUrl: "string",
    title: "꿈 속에서",
  },
  {
    isEighteen: false,
    musicId: 4,
    singer: "뉴진스",
    thumbnailUrl: "string",
    title: "하입보이",
  },
  {
    isEighteen: false,
    musicId: 5,
    singer: "루시",
    thumbnailUrl: "string",
    title: "조깅",
  },
];

/**
 * 마이페이지
 */
const Mypage = (): JSX.Element => {
  const [user, setUser] = useState(useRecoilValue(userState));
  const [dummyGender, setDummy] = useState<string>("none");

  useEffect(() => {
    if (user.gender == "M") {
      setDummy("남성");
    } else if (user.gender == "F") {
      setDummy("여성");
    }
  }, [user]);

  let age = 0;

  /** 나이 계산*/
  if (userState) {
    const today = moment(new Date());
    const birth = moment(`${user.birth}`, "YYYY-MM-DD");
    age = today.diff(birth, "years");
  }

  return (
    <StyledDiv>
      <p>마이페이지</p>
      <div className="profileDiv">
        <Profile name={user?.nickname || "none"} age={age} gender={dummyGender || "none"} id={user?.userid || 0} />
      </div>
      <div className="songHistoryDiv">
        <SongHistory musicList={MUSICLIST} />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  margin: 0px auto 0px;
  max-width: 430px;
  position: relative;

  ::-webkit-scrollbar {
    display: none;
  }

  & > p {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 184px;
    margin: 0px 0px 40px;
    font-size: 32px;
  }

  & .profileDiv {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 260px;
    box-sizing: border-box;
    padding: 0px 8px 0px;
  }

  & .songHistoryDiv {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 408px;
  }
`;

export default Mypage;
