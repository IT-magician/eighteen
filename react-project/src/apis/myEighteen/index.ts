import { instance } from "..";

/**
 * [GET]애창곡 목록 조회
 * @param page 페이지번호
 * @param size 페이지크기
 */
const getEighteenList = (page: number, size: number) => {
  return instance.get(`/my_eighteen`, {
    params: {
      page,
      size,
    },
  });
};

/**
 * [GET]애창곡 랜덤 조회
 */
const getEighteenRandom = () => {
  return instance.get(`/my_eighteen/random`);
};

/**
 * [POST]애창곡 추가
 * @param musicId 노래번호
 */
const addEighteen = (musicId: number) => {
  return instance.post(`/my_eighteen`, { musicId });
};

/**
 * [DELETE]애창곡 삭제
 * @param musicId 노래번호
 */
const removeEighteen = (musicId: number) => {
  return instance.delete(`/my_eighteen/${musicId}`);
};

export { getEighteenList, addEighteen, removeEighteen, getEighteenRandom };
