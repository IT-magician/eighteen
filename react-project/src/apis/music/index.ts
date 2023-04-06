import { instance } from "..";

/**
 * [GET] 검색어기반 노래 목록 조회
 * @param keyword 검색 키워드
 * @param page 페이지번호
 * @param size 페이지크기
 */
const getMusicList = (keyword: string, page: number, size: number, token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.get(`/music`, {
    params: {
      keyword,
      page,
      size,
    },
  });
};

/**
 * [GET] 노래 상세 정보 조회
 * @param musicId 노래번호
 */
const getMusic = (musicId: number, token: string) => {
  instance.defaults.headers["Authorization"] = token;
  return instance.get(`/music/${musicId}`);
};

export { getMusicList, getMusic };
