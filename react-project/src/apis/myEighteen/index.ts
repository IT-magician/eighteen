import { instance } from "..";

/**
 * [GET]애창곡 목록 조회
 * @param page 페이지번호
 * @param size 페이지크기
 */
const getEighteenList = (page: number, size: number) => {
  return instance.get(`/myEighteen`, {
    params: {
      page,
      size,
    },
  });
};

/**
 * [POST]애창곡 추가
 * @param musicId 노래번호
 */
const addEighteen = (musicId: number) => {
  return instance.post(`/myEighteen`, { musicId });
};

/**
 * [DELETE]애창곡 삭제
 * @param musicId 노래번호
 */
const removeEighteen = (musicId: number) => {
  return instance.delete(`/myEighteen/${musicId}`);
};

export { getEighteenList, addEighteen, removeEighteen };
