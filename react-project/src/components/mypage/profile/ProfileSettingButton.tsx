import React from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";
import { TbPencil } from "react-icons/tb";

const ProfileSettingButton = (): JSX.Element => {
  const navigate = useNavigate();

  const moveSetPage = () => {
    navigate("/setting");
  };

  return (
    <StyledButton onClick={moveSetPage}>
      <TbPencil className="icon" />
    </StyledButton>
  );
};

const StyledButton = styled.button`
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #080808;
  border: 0px;
  display: flex;
  justify-content: center;
  align-items: center;

  & .icon {
    width: 48px;
    height: 48px;
    color: white;
  }
`;

export default ProfileSettingButton;
