import { atom } from "recoil";

interface Search {
  keyword: string;
  type: string;
  loading: boolean;
}

const initialState: Search = {
  keyword: "",
  type: "title",
  loading: true,
};

export const searchState = atom({
  key: "search",
  default: initialState,
});
