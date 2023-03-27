import React from "react";
import styled from "styled-components";
import IconButton from "../common/button/IconButton";

/**
 * 프로필 이미지 수정 컴포넌트
 */
const SettingImgComponent = (): JSX.Element => {
  const clickTest = () => {
    console.log("setting!");
  };

  return (
    <StyledDiv>
      <img src="" alt=""></img>
      <div>
        <IconButton type="setting" onClick={clickTest} />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  width: 80px;
  height: 80px;

  & > img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background: white;
  }

  & > div {
    position: absolute;
    right: 0px;
    bottom: 0px;
  }
`;

export default SettingImgComponent;
