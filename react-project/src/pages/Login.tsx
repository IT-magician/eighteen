import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import { Pagination } from "swiper";
import { FirstLoginPage, SecondLoginPage, ThirdLoginPage, FinalLoginPage } from "../components/login/page";
import styled from "styled-components";

/**
 * 로그인 화면
 */
const Login = (): JSX.Element => {
  return (
    <StyledDiv>
      <Swiper pagination={true} modules={[Pagination]} className="mySwiper">
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
    </StyledDiv>
  );
};

const StyledDiv = styled.div`
  position: relative;

  & .swiper {
    width: 100%;
    height: 100%;

    & .swiper-slide {
      width: 100%;
      min-height: 750px;
    }
  }
  .swiper-pagination {
    /* position: relative; */
    margin-top: 16px;
    height: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
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
