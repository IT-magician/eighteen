import { atom } from "recoil";

/**
 * 사용자관련 STATE
 */
export const loadingState = atom<boolean>({
  key: "loading",
  default: false,
});
