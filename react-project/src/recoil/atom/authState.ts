import { atom } from "recoil";

interface Auth {
  token: string;
  loading: boolean;
}

/**
 * 사용자 인증 STATE
 */
export const authState = atom<Auth>({
  key: "loading",
  default: { token: "", loading: false },
});
