import axios from "axios";

// 환경변수에 선언된 backend server url을 이용합니다.
const SERVER_URL = process.env.REACT_APP_TEST_URL;

// 요청을 위한 공통 인스턴스를 선언하여 export합니다.
const instance = axios.create({
  baseURL: `${SERVER_URL}:8081`,
  // timeout: 3000,
  headers: {
    "Content-Type": "application/json",
    "x-forwarded-for-user-id": "kakao_2717143835",
  },
  withCredentials: true,
});

export { instance };
