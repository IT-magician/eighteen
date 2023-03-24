import React, { useEffect, useState } from "react";
import styled from "styled-components";
import BackButton from "../components/common/button/BackButton";
import SettingImg from "../components/setting/SettingImg";
import IconButton from "../components/common/button/IconButton";
import TextButton from "../components/common/button/TextButton";
import { useRecoilState, useRecoilValue } from "recoil";
import { userState } from "../recoil/atom";
import { Select } from "../components/common/select";
import { VerifyInput } from "../components/common/input/Verify";
import { nicknameVerify } from "../utils/validation";

type ProfileAttr = "nickname" | "birth" | "gender" | "email" | "profileImage";

/**
 * 프로필 수정 화면
 */
const Setting = (): JSX.Element => {
  const [user, setUser] = useState(useRecoilValue(userState));
  const [value, setValue] = useState<number>(0);
  const [pass, setPass] = useState<boolean>(true);

  useEffect(() => {
    if (user) return;

    // TODO : 사용자의 정보를 받아오는 과정 구현
    setUser({
      userid: 0,
      birth: "1999-03-23",
      gender: 0,
      nickname: "봉명동퉁퉁이",
      email: "test@gamil.com",
      profileImage: `${process.env.public_url}/user/undefined.png`,
    });
  }, []);

  const setNicname = (value: string) => {
    if (user) setUser({ ...user, nickname: value });
  };

  const clickTest = () => {
    console.log("save!");
  };

  return (
    <StyledDiv>
      <div className="backButtonDiv">
        <BackButton />
      </div>
      <p>프로필 설정</p>
      <div>
        <div>
          <SettingImg />
          <IconButton type="save" onClick={clickTest} />
        </div>
        <div className="birthSelectDiv">
          <Select<number>
            options={[
              { text: "남자", value: 0 },
              { text: "여자", value: 1 },
            ]}
            defaultIdx={value}
            setValue={setValue}
          />
          <Select<number>
            options={[
              { text: "남자", value: 0 },
              { text: "여자", value: 1 },
            ]}
            defaultIdx={value}
            setValue={setValue}
          />
          <Select<number>
            options={[
              { text: "남자", value: 0 },
              { text: "여자", value: 1 },
            ]}
            defaultIdx={value}
            setValue={setValue}
          />
        </div>
        <div>
          {user && (
            <VerifyInput value={user.nickname} setValue={setNicname} setPass={setPass} verify={nicknameVerify} />
          )}
          <Select<number>
            options={[
              { text: "남자", value: 0 },
              { text: "여자", value: 1 },
            ]}
            defaultIdx={value}
            setValue={setValue}
          />
        </div>
        <div className="exitButton">
          <button onClick={clickTest}>계정탈퇴</button>
        </div>
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  margin: 0px auto 0px;
  position: relative;
  overflow-x: hidden;

  ::-webkit-scrollbar {
    display: none;
  }

  & .backButtonDiv {
    margin-top: 48px;
    margin-left: 28px;
  }

  & > p {
    margin: 136px 0px 40px 28px;
    font-size: 32px;
  }

  & > div {
    width: 100%;
    box-sizing: border-box;
  }

  & > div > div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 40px;
    padding: 0px 36px 0px;
  }

  & .birthSelectDiv {
    justify-content: start;
  }

  & .exitButton {
    margin: 100px 0px 0px;
    justify-content: end;

    & > button {
      width: 144px;
      height: 40px;
      border-radius: 16px;
      border: 0px;
      background: #101010;
      color: #eef0f2;
    }
  }
`;

export default Setting;
