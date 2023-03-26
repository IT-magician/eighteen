import React, { useEffect, useState } from "react";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import styled from "styled-components";
import { Select } from "../../common/select";

const RecommendRankingSelector = (): JSX.Element => {
  const [search, setSearch] = useSearchParams();
  const [params, setParams] = useState({ age: search.get("age") || "0", gender: search.get("gender") || "0" });

  useEffect(() => {
    setSearch(params);
  }, [params]);

  return (
    <StyledDiv>
      <Select options={genderList} setValue={(gender) => setParams({ ...params, gender })} />
      <Select options={ageList} setValue={(age) => setParams({ ...params, age })} />
    </StyledDiv>
  );
};

const ageList = [
  { text: "10대", value: "1" },
  { text: "20대", value: "2" },
  { text: "30대", value: "3" },
  { text: "40대", value: "4" },
  { text: "50대", value: "5" },
];

const genderList = [
  { text: "남성", value: "0" },
  { text: "여성", value: "1" },
];

const StyledDiv = styled.div`
  position: absolute;
  z-index: 1;
  top: 0;
  display: flex;

  & > * {
    margin-right: 16px;
  }
`;

export default RecommendRankingSelector;
