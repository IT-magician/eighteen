import React, { useState, useEffect } from "react";
import styled from "styled-components";
import moment from "moment";
import { Profile } from "../components/mypage/profile";

interface IProfile {
  birth: string;
  gender: string;
  email: string;
  name: string;
}

const USERPROFILE: IProfile = {
  birth: "1997-03-28",
  gender: "남성",
  email: "mokbee27@gamil.com",
  name: "김태영",
};

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
      <div>
        <Profile
          name={userState ? userState?.name : "none"}
          age={age}
          gender={userState ? userState?.gender : "none"}
        />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  max-width: 430px;
  position: relative;

  & > p {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 184px;
    margin: 0px 0px 40px;
    font-size: 32px;
  }

  & > div {
    position: absolute;
    max-width: 374px;
    width: 100%;
    left: 28px;
    top: 260px;
    box-sizing: border-box;
    padding: 0px 8px 0px;
  }
`;

export default Mypage;
