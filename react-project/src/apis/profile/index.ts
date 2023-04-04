import { instance } from "..";

/**
 * [GET]프로필 조회
 */
const getProfile = (token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.get(`/profile`);
};

/**
 * [POST]프로필 이미지 등록
 * @param data img file 정보를 포함한 formData
 */
const uploadProfileImage = (data: FormData, token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.post(`/profile/image`, data, { headers: { "Content-Type": "multipart/form-data" } });
};

/**
 * [PATCH]프로필 정보 업데이트
 * @param data 사용자의 성별,연령,닉네임 데이터
 */
const modifyProfile = (data: FormData, token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.patch(`/profile`, data, { headers: { "Content-Type": "multipart/form-data" } });
};

/**
 * [GET]닉네임 중복 확인
 * @param nickname 닉네임
 */
const verifyNickname = async (nickname: string, token: string) => {
  instance.defaults.headers["Authorization"] = token;
  const { data } = await instance.get(`/profile/checkNickname`, { params: { nickname } });
  return data === "ok";
};

/**
 * [DELETE]회원탈퇴
 */
const deleteAccount = (token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.delete(`/profile`);
};

/**
 * [GET]최근 본 노래 목록 조회
 * @param musics 음악번호 리스트
 */
const getSongHistory = (musics: number[], token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.get(`/profile/history`, {
    params: { musics },
    paramsSerializer: {
      indexes: null,
    },
  });
};

export { getProfile, getSongHistory, verifyNickname, deleteAccount, modifyProfile, uploadProfileImage };
