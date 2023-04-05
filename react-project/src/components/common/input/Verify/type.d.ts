/**
 * 조건 판별 인터페이스
 */
export interface Verify {
  desc: string; // 판별 설명
  func(value: string): boolean | Promise<boolean>; // 판별 함수
  lazy?: boolean; // true인 경우 focusOut인 경우에만 판단
}

/**
 * 조건 판별 상태
 */
export type VerifyStatus = "default" | "fail" | "loading" | "pass";
