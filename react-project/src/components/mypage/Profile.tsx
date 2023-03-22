import React from "react";
import styled from "styled-components";
import { ProfileImage, ProfileInfo, ProfileName, SettingButton } from "./profileComponents";

const Profile = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <ProfileImage />
        <div>
          <ProfileName />
          <ProfileInfo />
        </div>
      </div>
      <SettingButton />
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
