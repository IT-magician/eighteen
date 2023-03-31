import React from "react";
import styled from "styled-components";
import { Autoplay } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";

interface Props {
  type: number;
  show?: boolean;
}

const Phone = ({ type, show }: Props): JSX.Element => {
  return (
    <StyledDiv className={show ? "" : "out"}>
      <Swiper
        slidesPerView={1}
        className="screen"
        modules={[Autoplay]}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false,
        }}
      >
        {type < PAGE_LIST.length &&
          PAGE_LIST[type].map((src, index) => (
            <SwiperSlide key={index}>
              <img src={src} />
            </SwiperSlide>
          ))}
      </Swiper>
      <img src={`${process.env.PUBLIC_URL}/phone/iphone.png`} />
    </StyledDiv>
  );
};

const PAGE_LIST = [
  [`${process.env.PUBLIC_URL}/phone/myEighteen.png`],
  [
    `${process.env.PUBLIC_URL}/phone/weather.png`,
    `${process.env.PUBLIC_URL}/phone/emotion.png`,
    `${process.env.PUBLIC_URL}/phone/situation.png`,
  ],
  [`${process.env.PUBLIC_URL}/phone/eighteenPage.png`],
];

const StyledDiv = styled.div`
  position: absolute;
  top: 320px;
  left: 50%;
  display: flex;
  justify-content: center;
  transform: translateX(-50%);
  width: 90%;
  max-width: 300px;
  aspect-ratio: 390/844;

  & > img {
    width: 100%;
    -webkit-animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
    animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
    animation-delay: 0.6s;
  }
  .screen {
    -webkit-animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
    animation: fade-in-bottom 0.6s cubic-bezier(0.39, 0.575, 0.565, 1) both;
    animation-delay: 0.6s;
    aspect-ratio: 390/844;
    overflow: hidden;
    margin-left: 2%;
    position: absolute;
    z-index: -1;
    align-self: center;
    width: 95.5%;
    border-radius: 8%;
    & img {
      width: 100%;
    }
  }
  &.out {
    & > * {
      -webkit-animation: slide-out-bottom 0.5s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
      animation: slide-out-bottom 0.5s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
    }
  }
`;
export default Phone;
