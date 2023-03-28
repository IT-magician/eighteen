import React, { useEffect, useRef } from "react";
import styled from "styled-components";
import SongSearchInput from "./SongSearchInput";

const SongSearchComponent = (): JSX.Element => {
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const addBackground = (e: Event) => {
      if (e.target instanceof HTMLDivElement) {
        if (ref.current && e.target.scrollTop > 120) {
          ref.current.classList.add("bg");
          document.querySelector("#Page")?.removeEventListener("scroll", addBackground);
          document.querySelector("#Page")?.addEventListener("scroll", removeBackground);
        }
      }
    };

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

  return (
    <StyledDiv ref={ref}>
      <SongSearchInput />
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
`;

export default SongSearchComponent;
