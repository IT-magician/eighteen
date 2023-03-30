import React, { useState, useRef } from "react";
import styled from "styled-components";
import IconButton from "../common/button/IconButton";

interface Props {
  image: string;
  setValue(value: File): void;
}

/**
 * 프로필 이미지 수정 컴포넌트
 */
const SettingImgComponent = ({ image, setValue }: Props): JSX.Element => {
  const [imgFile, setImgFile] = useState<string>("");
  const imgRef = useRef<HTMLInputElement>(null);

  const saveImgFile = () => {
    if (imgRef.current && imgRef.current.files != null) {
      const file = imgRef.current.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
        if (typeof reader.result === "string") {
          setImgFile(reader.result);
          setValue(file);
        }
      };
    }
  };

  /**
   * input태그 핸들링 함수
   */
  const onHandleImgInput = () => {
    const uploadImgFile = document.getElementById("profileImg");
    if (uploadImgFile) uploadImgFile.click();
  };

  return (
    <StyledDiv>
      <img src={imgFile ? imgFile : image} alt="프로필 이미지"></img>
      <form>
        <label className="signup-profileImg-label" htmlFor="profileImg" />
        <input
          className="signup-profileImg-input"
          type="file"
          accept="image/*"
          id="profileImg"
          onChange={saveImgFile}
          ref={imgRef}
        />
      </form>
      <div>
        <IconButton type="setting" onClick={onHandleImgInput} />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  width: 80px;
  height: 80px;

  & > img {
    height: 100%;
    aspect-ratio: 1/1;
    border-radius: 50%;
    background: white;
  }

  & > form > label {
    display: none;
  }

  & > form > input {
    display: none;
  }

  & > div {
    position: absolute;
    right: 0px;
    bottom: 0px;
  }
`;

export default SettingImgComponent;
