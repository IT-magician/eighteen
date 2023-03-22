import React from "react";
import styled from "styled-components";
import { ProfileImage, ProfileInfo, ProfileName, SettingButton } from "./profileComponents";

const Profile = (): JSX.Element => {
  return (
    <StyledDiv>
      <div>
        <ProfileImage />
        <ProfileName />
        <ProfileInfo />
      </div>
      <div>
        <SettingButton />
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  padding: 0px 8px 0px;
  display: flex;
  align-items: center;
`;

export default Profile;
