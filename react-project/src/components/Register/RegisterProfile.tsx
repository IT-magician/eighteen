import React, { useState } from "react";
import styled from "styled-components";
import DatePicker from "react-datepicker";

const RegisterProfile = (): JSX.Element => {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <StyledDiv>
      <h1>
        어서오세요!
        <br />
        에이틴은 처음이죠?
      </h1>
      <h2>당신을 위한 노래 추천을 할 수 있도록 당신에 대해 알려주세요</h2>

      <DatePicker selected={startDate} onChange={(date) => setStartDate(date ?? new Date())} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div``;

export default RegisterProfile;
