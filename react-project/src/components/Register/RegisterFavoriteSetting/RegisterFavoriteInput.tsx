import React, { useEffect } from "react";
import { TbMusic, TbSearch, TbUser } from "react-icons/tb";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { searchState } from "../../../recoil/atom/searchState";

const RegisterFavoriteInput = (): JSX.Element => {
  const [search, setSearch] = useRecoilState(searchState);

  useEffect(() => {
    setSearch({ ...search, keyword: "", type: "title" });
  }, []);

  const { keyword, type } = search;

  const onChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch({ ...search, keyword: e.target.value });
  };

  const onChangeType = (e: React.MouseEvent<HTMLButtonElement>) => {
    setSearch({ ...search, type: e.currentTarget.value });
  };

  return (
    <StyledDiv tabIndex={1}>
      <TbSearch />
      <input value={keyword} onChange={onChangeKeyword} placeholder={"노래를 검색해보세요"} />
      <div className="type-button">
        {type === "singer" && (
          <button value={"title"} onClick={onChangeType}>
            <TbUser />
            <span>가수</span>
          </button>
        )}
        {type === "title" && (
          <button value={"singer"} onClick={onChangeType}>
            <TbMusic />
            <span>제목</span>
          </button>
        )}
      </div>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  background-color: var(--blue-500);
  box-sizing: border-box;
  height: 48px;
  border-radius: 16px;
  display: flex;
  align-items: center;
  padding: 8px;
  box-shadow: var(--shadow);
  & > svg {
    font-size: 28px;
    stroke-width: 3px;
  }

  & > input {
    margin: auto 8px;
    width: 0;
    flex: 1 1 0;
    background: none;
    border: 0;
    font: inherit;
    color: var(--black-50);
    letter-spacing: -0.05em;

    &::placeholder {
      color: var(--blue-200);
      font: inherit;
    }
    &:hover {
      outline: 0;
    }
  }

  & > .type-button > button {
    height: 40px;
    width: 40px;
    border: 0;
    border-radius: 20px;
    background-color: var(--blue-600);
    color: var(--black-50);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font: inherit;
    & span {
      display: block;
      font: inherit;
      font-size: 8px;
    }
  }
`;

export default RegisterFavoriteInput;
