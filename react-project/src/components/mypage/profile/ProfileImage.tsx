import React from "react";
import styled from "styled-components";

interface Props {
  image: string;
}

/**
 * 이미지 프로필 컴포넌트
 */
const ProfileImage = ({ image }: Props): JSX.Element => {
  const imgURL = `${image}`;
  const dummyAlt = `eightten-logo.png`;

  return (
    <StyledDiv>
      <img src={imgURL} alt={dummyAlt} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  margin-right: 16px;
  width: 80px;
  height: 80px;
  background: white;
  border-radius: 50%;

  & > img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
  }
`;

export default ProfileImage;
