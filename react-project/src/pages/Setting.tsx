import React from "react";
import styled from "styled-components";
import BackButton from "../components/common/button/BackButton";
import SettingImg from "../components/setting/settingImgComponent/SettingImg";
import IconButton from "../components/common/button/IconButton";
import SettingInfo from "../components/setting/settingInfoComponent/SettingInfo";

/**
 * 프로필 수정 화면
 */
const Setting = (): JSX.Element => {
  const clickTest = () => {
    console.log("save!");
  };

  return (
    <StyledDiv>
      <BackButton />
      <p>프로필 설정</p>
      <div>
        <div>
          <SettingImg />
          <IconButton type="save" onClick={clickTest} />
        </div>
        <SettingInfo />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  margin: 0px auto 0px;
  position: relative;

  ::-webkit-scrollbar {
    display: none;
  }

  & > p {
    position: absolute;
    top: 184px;
    margin: 0px 0px 40px 28px;
    font-size: 32px;
  }

  & > div {
    position: absolute;
    top: 248px;
    width: 100%;
    box-sizing: border-box;
  }

  & > div > div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0px 36px 0px;
  }
`;

export default Setting;
