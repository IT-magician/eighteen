import React, { useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination } from "swiper";
import { FirstLoginPage, SecondLoginPage, ThirdLoginPage, FinalLoginPage } from "../components/login/page";
import styled from "styled-components";
import { Phone } from "../components/common/phone";
import { EnjoyHandBg } from "../components/common/background";

/**
 * 로그인 화면
 */
const Login = (): JSX.Element => {
  const [page, setPage] = useState<number>(0);

  return (
    <StyledDiv>
      <Swiper
        pagination={true}
        modules={[Pagination]}
        className="mySwiper"
        onSlideChange={(swiper) => setPage(swiper.activeIndex)}
      >
        <SwiperSlide>
          <FirstLoginPage />
        </SwiperSlide>
        <SwiperSlide>
          <SecondLoginPage />
        </SwiperSlide>
        <SwiperSlide>
          <ThirdLoginPage />
        </SwiperSlide>
        <SwiperSlide>
          <FinalLoginPage />
        </SwiperSlide>
      </Swiper>

      <Phone type={page} show={page < 3} />
      <EnjoyHandBg show={page === 3} />
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;
  height: 100%;
  overflow: hidden;

  & * {
    box-sizing: border-box;
  }
  & > .swiper {
    width: 100%;
    height: 100%;
    & .swiper-slide {
      opacity: 0;
      width: 100%;
      height: 100%;
      & > div {
        padding: 120px 32px 0;
        & h1,
        p,
        span {
          font-weight: 400;
          display: block;
          word-break: keep-all;
        }

        & > h1 {
          font-size: 28px;
          margin-bottom: 32px;
          min-height: 40px;
        }
        & > p {
          font-size: 20px;
        }
        & span {
          margin: 8px 0;
        }
      }
    }
    & .swiper-slide-active {
      opacity: 1;
      & h1,
      & p,
      & div > div {
        -webkit-animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
        animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
      }
      & p {
        animation-delay: 0.4s;
      }
      & div > div {
        animation-delay: 0.8s;
      }
    }
  }
  .swiper-pagination {
    /* position: relative; */
    margin: 16px auto 80px;
    height: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  &::after {
    position: absolute;
    bottom: 0;
    content: "";
    width: 100%;
    height: 140px;
    background: linear-gradient(rgba(0, 0, 0, 0), var(--black-900));
  }

  .swiper-pagination-bullet {
    background-color: var(--black-50);
    opacity: 1;
    width: 12px;
    height: 12px;
    transition: all 0.3s;
  }
  .swiper-pagination-bullet-active {
    background-color: var(--blue-500);
    width: 16px;
    height: 16px;
  }
`;

export default Login;
