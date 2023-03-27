import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";
import BackButton from "../components/common/button/BackButton";
import SettingImg from "../components/setting/SettingImg";
import IconButton from "../components/common/button/IconButton";
import { useRecoilState, useRecoilValue } from "recoil";
import { userState } from "../recoil/atom";
import { Select } from "../components/common/select";
import { VerifyInput } from "../components/common/input/Verify";
import { nicknameVerify } from "../utils/validation";
import { modifyProfile, deleteAccount } from "../apis/profile";
import SettingDatePicker from "../components/setting/SettingDatePicker";

type ProfileAttr = "nickname" | "birth" | "gender" | "email" | "profileImage";

/**
 * recoil에서 받아오는 사용자 정보 형식
 * user: {
    userid: 0,
    nickname: "봉명동퉁퉁이",
    birth: "1999-03-23",
    gender: "F",
    email: "test@gamil.com",
    profileImage: `${process.env.public_url}/user/undefined.png`,
  }
 */

/**
 * 프로필 수정 화면
 */
const Setting = (): JSX.Element => {
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const [user, setUser] = useState(useRecoilValue(userState));
  const [value, setValue] = useState<number>(0);
  const [pass, setPass] = useState<boolean>(true);

  const navigate = useNavigate();

  /*
   *useState에 담긴 사용자 이름 수정
   */
  const setNicname = (value: string) => {
    if (user) {
      setUser({ ...user, nickname: value });
    }
  };

  /*
   *useState에 담긴 사용자 나이 수정
   */
  const setBirth = (value: string) => {
    if (user) {
      setUser({ ...user, birth: value });
    }
  };

  /*
   * useState에 담긴 사용자 성별 수정
   */
  const setGender = (value: "M" | "F") => {
    if (user) {
      setUser({ ...user, gender: value });
    }
  };

  /*
   * 사용자 정보 수정 로직
   */
  const onHandleModifyProfile = () => {
    const newProfile = { nickname: user.nickname, gender: user.gender, birth: user.birth };
    setUserInfo(user);
    modifyProfile(newProfile);
    navigate("/mypage");
  };

  const onHandleDeleteAccount = () => {
    deleteAccount();
    navigate("/");
  };

  return (
    <StyledDiv>
      <div className="backButtonDiv">
        <BackButton />
      </div>
      <p>프로필 설정</p>
      <div>
        <div className="imageDiv">
          <SettingImg />
          <IconButton type="save" onClick={onHandleModifyProfile} />
        </div>
        <div className="birthSelectDiv">
          <SettingDatePicker setValue={setBirth} />
        </div>
        <div>
          {user && (
            <VerifyInput value={user.nickname} setValue={setNicname} setPass={setPass} verify={nicknameVerify} />
          )}
          <Select<string>
            options={[
              { text: "남자", value: "M" },
              { text: "여자", value: "F" },
            ]}
            defaultIdx={value}
            setValue={setGender}
          />
        </div>
        <div className="exitButton">
          <button onClick={onHandleDeleteAccount}>계정탈퇴</button>
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
    margin: 90px 0px 40px 28px;
    font-size: 32px;
  }

  & > div {
    width: 100%;
    box-sizing: border-box;
  }

  & .imageDiv {
    align-items: center;
  }

  & > div > div {
    display: flex;
    justify-content: space-between;
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
