import React from "react";
import styled from "styled-components";

interface Props {
  age: number;
  gender: string;
}

const ProfileInfo = ({ age, gender }: Props): JSX.Element => {
  return (
    <StyledP>
      {age}세·{gender}
    </StyledP>
  );
};

const StyledP = styled.p`
  margin: 0px;
  font-size: 16px;
`;

export default ProfileInfo;
