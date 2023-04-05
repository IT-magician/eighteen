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

  const onDefaultImg = (e: React.SyntheticEvent<HTMLImageElement>) => {
    e.currentTarget.src = `${process.env.PUBLIC_URL}/img/default_profile.png`;
  };

  return (
    <StyledDiv>
      <img src={imgURL} alt={dummyAlt} onError={onDefaultImg} />
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
