import { atom } from "recoil";

interface User {
  userid: number;
  nickname: string;
  birth: string;
  gender: "M" | "F";
  email: string;
  profileImage: string;
}

/**
 * 사용자관련 STATE
 */
export const userState = atom<User>({
  key: "user",
  default: {
    userid: 0,
    nickname: "봉명동퉁퉁이",
    birth: "1999-03-23",
    gender: "F",
    email: "test@gamil.com",
    profileImage: `${process.env.public_url}/user/undefined.png`,
  },
});
