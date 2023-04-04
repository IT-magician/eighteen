import React, { useEffect, useRef, useState } from "react";
import { TbX } from "react-icons/tb";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { searchState } from "../../../recoil/atom/searchState";
import SongSearchInput from "./SongSearchInput";

const SongSearchComponent = (): JSX.Element => {
  const ref = useRef<HTMLDivElement>(null);
  const [search, setSearch] = useRecoilState(searchState);
  const [history, setHistory] = useState<{ keyword: string; type: string }[]>(
    JSON.parse(localStorage.getItem(`history`) || "[]"),
  );

  // 스크롤에 따른 백그라운드 추가를 위한 useEffect
  useEffect(() => {
    // 상단 백그라운드 추가 이벤트
    const addBackground = (e: Event) => {
      if (e.target instanceof HTMLDivElement) {
        if (ref.current && e.target.scrollTop > 120) {
          ref.current.classList.add("bg");
          document.querySelector("#Page")?.removeEventListener("scroll", addBackground);
          document.querySelector("#Page")?.addEventListener("scroll", removeBackground);
        }
      }
    };
    // 상단 백그라운드 삭제 이벤트
    const removeBackground = (e: Event) => {
      if (e.target instanceof HTMLDivElement) {
        if (ref.current && e.target.scrollTop <= 120) {
          ref.current.classList.remove("bg");
          document.querySelector("#Page")?.removeEventListener("scroll", removeBackground);
          document.querySelector("#Page")?.addEventListener("scroll", addBackground);
        }
      }
    };

    document.querySelector("#Page")?.addEventListener("scroll", addBackground);
    return () => {
      document.querySelector("#Page")?.removeEventListener("scroll", addBackground);
      document.querySelector("#Page")?.removeEventListener("scroll", removeBackground);
    };
  }, []);

  // 검색 기록 저장을 위한 useEffect
  useEffect(() => {
    localStorage.setItem(`history`, JSON.stringify(history));
  }, [history]);

  const onClick = (e: React.MouseEvent<HTMLLIElement>) => {
    if (e.currentTarget instanceof HTMLElement) {
      const { keyword, type } = e.currentTarget.dataset;
      if (keyword && type) {
        setSearch({ ...search, keyword, type });
        onAdd(keyword, type);
      }
    }
  };
  const onDelete = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    const { keyword, type } = JSON.parse(e.currentTarget.value);
    setHistory([...history.filter((item) => item.keyword !== keyword || item.type !== type)]);
  };
  const onAdd = (keyword: string, type: string) => {
    setHistory([
      { keyword, type },
      ...history.filter((item) => item.keyword !== keyword || item.type !== type).slice(0, 4),
    ]);
  };

  return (
    <StyledDiv ref={ref}>
      <SongSearchInput addHistory={onAdd} />
      <ul>
        {history.map((item, index) => (
          <li className="history" key={index} data-keyword={item.keyword} data-type={item.type} onClick={onClick}>
            <span>{item.keyword}</span>
            <button value={JSON.stringify(item)} onClick={onDelete}>
              <TbX />
            </button>
          </li>
        ))}
      </ul>
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  top: 80px;
  position: sticky;
  z-index: 1;

  &.bg::after {
    content: "";
    background: linear-gradient(to top, transparent 0.1%, var(--black-900));
    z-index: -1;
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 240px;
    pointer-events: none;
  }

  & .history {
    width: fit-content;
    max-width: 200px;
    height: 40px;
    display: flex;
    align-items: center;
    background-color: var(--black-500);
    color: var(--black-50);
    font: inherit;
    font-weight: 400;
    margin: 4px;
    border: 0;
    border-radius: 20px;
    box-sizing: border-box;
    padding: 0 16px;

    & > span {
      flex: 1 1 0;
      width: 100%;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    & > button {
      width: 24px;
      height: 24px;
      margin-left: 8px;
      margin-right: -8px;
      border: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 24px;
      background: none;
      color: var(--black-100);
    }
  }

  & > ul {
    margin: 4px 0;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
  }
`;

export default SongSearchComponent;
