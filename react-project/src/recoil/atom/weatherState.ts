import { atom } from "recoil";
import { weather as WeatherType } from "../../apis/weather";

export interface Weather {
  weather: WeatherType;
  temperature: number;
  text: string;
  img: string;
}

/**
 * 날씨관련 state
 */
export const weatherState = atom<Weather>({
  key: "weather",
  default: undefined,
});
