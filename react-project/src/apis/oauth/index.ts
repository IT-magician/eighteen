import { instance } from "..";
import { OAuth } from "./type";

/**
 * [GET]access token 요청
 */
export const checkUser = async () => {
  const response = await instance.get(`/auth/reIssue`);
  sessionStorage.setItem("access-token", response.headers["accessToken"]);
  instance.defaults.headers["Authorization"] = `Bearer ${sessionStorage.getItem("access-token")}`;
  return response;
};

export const login = (type: OAuth) => {
  window.location.href = `${process.env.REACT_APP_SERVER_URL}/oauth2/authorization/${type}`;
};
