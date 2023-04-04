import { instance } from "..";

export const getMainData = (token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.get(`/main`);
};
