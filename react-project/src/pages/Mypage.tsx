import React, { useState, useEffect } from "react";
import styled from "styled-components";
import moment from "moment";
import { Profile } from "../components/mypage/profile";
import { SongHistory } from "../components/mypage/songHistory";

interface IProfile {
  birth: string;
  gender: string;
  email: string;
  name: string;
  userId: string;
}

const USERPROFILE: IProfile = {
  birth: "1997-03-28",
  gender: "남성",
  email: "mokbee27@gamil.com",
  name: "김태영",
  userId: "1",
};

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
  const [userState, setUser] = useState<IProfile>();

  useEffect(() => {
    async function userUpdate() {
      const userProfile = USERPROFILE;
      setUser(userProfile);
    }

    userUpdate();
  }, []);

  let age = 0;

  /** 나이 계산*/
  if (userState) {
    const today = moment(new Date());
    const birth = moment(`${userState.birth}`, "YYYY-MM-DD");
    age = today.diff(birth, "years");
  }

  return (
    <StyledDiv>
      <p>마이페이지</p>
      <div className="profileDiv">
        <Profile
          name={userState?.name || "none"}
          age={age}
          gender={userState?.gender || "none"}
          id={userState?.userId || "none"}
        />
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
