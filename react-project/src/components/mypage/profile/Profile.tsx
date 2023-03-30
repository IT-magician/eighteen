import React from "react";
import styled from "styled-components";
import { ProfileImage, ProfileInfo, ProfileName, ProfileSettingButton } from ".";

interface Props {
  age: number;
  gender: string;
  name: string;
  image: string;
}

const Profile = ({ name, age, gender, image }: Props): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <ProfileImage image={image} />
        <div>
          <ProfileName name={name} />
          <ProfileInfo age={age} gender={gender} />
        </div>
      </div>
      <ProfileSettingButton />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;

  & > div {
    display: flex;

    & > div {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
  }
`;

export default Profile;
