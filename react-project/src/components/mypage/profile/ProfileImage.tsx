import React from "react";
import styled from "styled-components";

interface Props {
  id: string;
}

/**
 * 해당 페이지는 페이지 템플릿으로,
 * 추후 페이지 추가 시 이를 복사하여 사용하는 것을 추천합니다.
 */
const ProfileImage = ({ id }: Props): JSX.Element => {
  const imgURL = `${id}`;
  const dummyAlt = `${id}-image`;

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
`;

export default ProfileImage;
