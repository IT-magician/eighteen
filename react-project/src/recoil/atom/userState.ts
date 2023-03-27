import { atom } from "recoil";

interface User {
  userid: number;
  nickname: string;
  birth: string;
  gender: 0 | 1;
}

/**
 * 사용자관련 STATE
 */
export const userState = atom<User>({
  key: "user",
  default: undefined,
});
