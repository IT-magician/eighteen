import { instance } from "..";

/**
 * [GET]프로필 조회
 */
const getProfile = () => {
  return instance.get(`/profile`);
};

/**
 * [POST]프로필 이미지 등록
 * @param data img file 정보를 포함한 formData
 */
const uploadProfileImage = (data: FormData) => {
  return instance.post(`/profile/image`, data, { headers: { "Content-Type": "multipart/form-data" } });
};

/**
 * [PATCH]프로필 정보 업데이트
 * @param data 사용자의 성별,연령,닉네임 데이터
 */
const modifyProfile = (data: FormData) => {
  return instance.patch(`/profile`, data, { headers: { "Content-Type": "multipart/form-data" } });
};

/**
 * [GET]닉네임 중복 확인
 * @param nickname 닉네임
 */
const verifyNickname = (nickname: string) => {
  return instance.get(`/profile/checkNickname`, { params: { nickname } });
};

/**
 * [DELETE]회원탈퇴
 */
const deleteAccount = () => {
  return instance.delete(`/profile`);
};

/**
 * [GET]최근 본 노래 목록 조회
 * @param musics 음악번호 리스트
 */
const getSongHistory = (musics: number[]) => {
  return instance.get(`/propfile/history`, {
    params: { musics },
    paramsSerializer: {
      encode(paramObj) {
        const params = new URLSearchParams();
        for (const key in paramObj) {
          params.append(key, paramObj[key]);
        }

        return params.toString();
      },
    },
  });
};

export { getProfile, getSongHistory, verifyNickname, deleteAccount, modifyProfile, uploadProfileImage };
