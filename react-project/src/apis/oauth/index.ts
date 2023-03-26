import { instance } from "..";

/**
 * [GET]access token 요청
 */
const checkUser = () => {
  return instance.get(`/oauth2/check`);
};

export default checkUser;
