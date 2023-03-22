import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Verify, VerifyStatus } from "./type";
import VerifyItem from "./VerifyItem";

interface Props {
  value: string;
  lazyValue: string;
  verify: Verify[];
  status: VerifyStatus;
  setStatus(pass: VerifyStatus): void;
}

interface VerifyResult {
  desc: string;
  status: VerifyStatus;
  lazy?: boolean;
}

/**
 * 조건 목록 컴포넌트
 */
const VerifyList = ({ value, lazyValue, verify, status, setStatus }: Props): JSX.Element => {
  const [verifyResult, setVerifyResult] = useState<VerifyResult[]>([]);

  useEffect(() => {
    // 최초 판별 조건에 대해 default 상태로 설정합니다.
    setVerifyResult(verify.map((item) => ({ desc: item.desc, status: "default", lazy: item.lazy })));
  }, []);

  useEffect(() => {
    updateVerifyResult();
  }, [value, lazyValue]);

  // 판별 결과 목록 업데이트 함수
  const updateVerifyResult = async () => {
    // 초기 비어있는 상태인 경우 함수 종료
    if (!value && status === "default") return;

    const newVerifyResult: VerifyResult[] = await Promise.all(
      verify.map(async (item) =>
        item.lazy
          ? { desc: item.desc, status: (await item.func(lazyValue)) ? "pass" : "fail", lazy: true }
          : { desc: item.desc, status: (await item.func(value)) ? "pass" : "fail" },
      ),
    );

    setVerifyResult(newVerifyResult);

    if (newVerifyResult.find((item) => item.status === "fail")) setStatus("fail");
    else setStatus("pass");
  };

  return (
    <StyledUl>
      {verifyResult
        .filter((item) => lazyValue || !item.lazy)
        .map((item, index) => (
          <VerifyItem key={index} status={item.status} desc={item.desc} />
        ))}
    </StyledUl>
  );
};

const StyledUl = styled.ul`
  margin: 0;
  padding: 0;
`;

export default VerifyList;
