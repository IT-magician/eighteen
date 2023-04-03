import { verifyNickname } from "../../apis/profile";
import { Verify } from "../../components/common/input/Verify/type";

const checkNicknameString = (value: string): boolean => {
  const regExp = /^[0-9a-zA-Z가-힣]+$/;
  return !!regExp.exec(value);
};

const checkNicknameLength = (value: string): boolean => {
  return value.length >= 2 && value.length <= 8;
};

const nicknameVerify: Verify[] = [
  { desc: "한글, 영어, 숫자로 구성", func: checkNicknameString },
  { desc: "2글자 이상 8글자 이하", func: checkNicknameLength },
  { desc: "사용가능한 닉네임", func: verifyNickname, lazy: true },
];

export default nicknameVerify;
