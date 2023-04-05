import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import styled from "styled-components";
import { Select } from "../../common/select";
import { useRecoilValue } from "recoil";
import { userState } from "../../../recoil/atom";
import moment from "moment";

const RecommendRankingSelector = (): JSX.Element => {
  const user = useRecoilValue(userState);
  const [search, setSearch] = useSearchParams();
  const [params, setParams] = useState({
    age: search.get("age") || `${getAge(user?.birth ?? "1999-01-01")}`,
    gender: search.get("gender") || user?.gender || "M",
  });

  useEffect(() => {
    setSearch(params, { replace: true });
  }, [params]);

  return (
    <StyledDiv>
      <Select options={genderList} value={params.gender} setValue={(gender) => setParams({ ...params, gender })} />
      <Select options={ageList} value={params.age} setValue={(age) => setParams({ ...params, age })} />
    </StyledDiv>
  );
};

const ageList = [
  { text: "10대", value: "1" },
  { text: "20대", value: "2" },
  { text: "30대", value: "3" },
  { text: "40대", value: "4" },
  { text: "50대", value: "5" },
  { text: "60대 +", value: "6" },
];

const genderList = [
  { text: "남성", value: "M" },
  { text: "여성", value: "F" },
];

const getAge = (birth: string): number => {
  const today = moment(new Date());
  const age = today.diff(moment(birth, "YYYY-MM-DD"), "years");
  const ageId = Math.floor(age / 10) ?? 1;
  return ageId > 6 ? 6 : ageId;
};

const StyledDiv = styled.div`
  position: absolute;
  z-index: 1;
  top: 0;
  display: flex;
  margin: 0 16px;

  & > * {
    margin-right: 16px;
  }
`;

export default RecommendRankingSelector;
