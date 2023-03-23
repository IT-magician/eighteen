import React from "react";
import styled from "styled-components";

interface Props {
  name: string;
}

const ProfileInfo = ({ name }: Props): JSX.Element => {
  return <StyledP>{name}님</StyledP>;
};

const StyledP = styled.p`
  margin: 0px 0px 8px 0px;
  font-size: 24px;
`;

export default ProfileInfo;
