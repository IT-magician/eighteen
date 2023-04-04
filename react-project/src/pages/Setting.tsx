import React, { useRef, useState } from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";
import BackButton from "../components/common/button/BackButton";
import SettingImg from "../components/setting/SettingImg";
import IconButton from "../components/common/button/IconButton";
import { useRecoilState } from "recoil";
import { userState } from "../recoil/atom";
import { Select } from "../components/common/select";
import { VerifyInput } from "../components/common/input/Verify";
import { nicknameVerify } from "../utils/validation";
import { modifyProfile, deleteAccount } from "../apis/profile";
import SettingDatePicker from "../components/setting/SettingDatePicker";
import { authState } from "../recoil/atom/authState";
import axios from "axios";
import { User } from "../recoil/atom/userState";

// type ProfileAttr = "nickname" | "birth" | "gender" | "email" | "profileImage";

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
  const [globalUser, setGlobalUser] = useRecoilState(userState);
  if (!globalUser) return <></>;
  const [auth, setAuth] = useRecoilState(authState);
  const [user, setUser] = useState<User>({ ...globalUser });
  const [pass, setPass] = useState<boolean>(true);
  const file = useRef<File>();
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
   * userState에 담긴 사용자 이미지 수정
   */
  const setImage = (value: File) => {
    if (user) {
      file.current = value;
    }
  };

  /*
   * 사용자 정보 수정 로직
   */
  const onHandleModifyProfile = async () => {
    const formData = new FormData();
    const newProfile = JSON.stringify({
      nickname: user?.nickname,
      gender: user?.gender,
      birth: user?.birth,
    });
    formData.append("profileInfo", new Blob([newProfile], { type: "application/json" }));
    if (file.current instanceof File) formData.append("profileImage", file.current);
    try {
      const res = await modifyProfile(formData, auth.token);
      if (res.data == "ok") {
        navigate("/mypage");
      }
    } catch (e) {
      if (axios.isAxiosError(e)) {
        if (e.response?.status === 401) {
          setAuth({ ...auth, token: "" });
        }
      }
    }
  };

  const onHandleDeleteAccount = async () => {
    try {
      const res = await deleteAccount(auth.token);
      if (res) {
        setGlobalUser(null);
      }
    } catch (e) {
      if (axios.isAxiosError(e)) {
        if (e.response?.status === 401) {
          setAuth({ ...auth, token: "" });
        }
      }
    }
  };

  return (
    <StyledDiv>
      <div className="backButtonDiv">
        <BackButton />
      </div>
      <h1>프로필 설정</h1>
      <div>
        <div className="imageDiv">
          <SettingImg image={user.profileImage} setValue={setImage} />
          <IconButton type="save" onClick={onHandleModifyProfile} />
        </div>
        <div className="birthSelectDiv">
          <SettingDatePicker setValue={setBirth} birth={user.birth} />
        </div>
        <div className="NameGenderDiv">
          {user && (
            <VerifyInput
              value={user.nickname}
              setValue={setNicname}
              setPass={setPass}
              verify={nicknameVerify(auth.token)}
            />
          )}
          <Select<string>
            options={[
              { text: "남자", value: "M" },
              { text: "여자", value: "F" },
            ]}
            value={user.gender}
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
  box-sizing: border-box;
  padding: 48px 16px 80px;
  overflow-x: hidden;

  ::-webkit-scrollbar {
    display: none;
  }

  & .backButtonDiv {
    margin-bottom: 23.44px;
  }

  & > h1 {
    margin-bottom: 40px;
    font-weight: 400;
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
    padding: 0px 8px 0px;
  }

  & .birthSelectDiv {
    justify-content: start;
  }

  & .NameGenderDiv {
    height: 100px;
  }

  & .exitButton {
    margin: 180px 0px 0px;
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
