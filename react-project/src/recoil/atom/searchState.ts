import { atom } from "recoil";

interface Search {
  keyword: string;
  type: string;
  loading: boolean;
  page: number;
}

const initialState: Search = {
  keyword: "",
  type: "title",
  loading: false,
  page: 0,
};

export const searchState = atom({
  key: "search",
  default: initialState,
});
