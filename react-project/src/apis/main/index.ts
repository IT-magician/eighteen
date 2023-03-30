import { instance } from "..";

export const getMainData = () => {
  return instance.get(`/main`);
};
