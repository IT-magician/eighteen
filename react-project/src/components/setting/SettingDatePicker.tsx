import React, { useEffect, useState } from "react";
import styled from "styled-components";
import DatePicker from "react-datepicker";
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";

interface Props {
  birth: string;
  setValue(value: string): void;
}

/**
 * 수정 페이지에 삽입할 캘린터 컴포넌트입니다.
 */
const SettingDatePicker = ({ birth, setValue }: Props): JSX.Element => {
  const [birthDate, setBirthDate] = useState<Date | null>(new Date(birth));

  useEffect(() => {
    if (birthDate) {
      const dummy = moment(birthDate).format("YYYY-MM-DD");
      setValue(dummy);
    }
  }, [birthDate]);

  return (
    <StyledDiv>
      <DatePicker
        className="DatePicker"
        locale={ko}
        dateFormat="yyyy-MM-dd"
        closeOnScroll={true}
        placeholderText=""
        selected={birthDate}
        onChange={(date) => setBirthDate(date)}
      />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  & .DatePicker {
    box-sizing: border-box;
    padding: 0px 16px 0px;
    width: 240px;
    height: 40px;
    font-size: 18px;
    color: #e7e7e7;
    background: #101010;
    border: 0px;
  }
`;

export default SettingDatePicker;
