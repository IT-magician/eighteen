import { atom } from "recoil";

interface User {
  nickname: string;
  birth: string;
  gender: "M" | "F";
  profileImage: string;
}

/**
 * 사용자관련 STATE
 */
export const userState = atom<User | null>({
  key: "user",
  default: null,
});
