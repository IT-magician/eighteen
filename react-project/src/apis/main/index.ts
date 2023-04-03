import { instance } from "..";

export const getMainData = () => {
  instance.defaults.headers["Authorization"] = `Bearer ${sessionStorage.getItem("access-token")}`;
  return instance.get(`/main`);
};
