package kopo.jjh.prj.mapper.impl;

import kopo.jjh.prj.security.dto.AccountForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {

    // 회원 가입하기 (회원 정보 등록)
    int insertUserInfo(AccountForm pDTO) throws Exception;

    // 회원 가입 전 중복체크 (DB조회하기)
    AccountForm getUserExists(AccountForm pDTO) throws Exception;

    // For Login
    AccountForm getUserLoginCheck(AccountForm pDTO) throws Exception;

    // 오답 문자를 업데이트
    int updateWrongCh(AccountForm pDTO) throws Exception;

    // 오답문자를 JSP에 로드하기 위함
    AccountForm getWrongCh(AccountForm pDTO) throws Exception;

    AccountForm existCheck(AccountForm pDTO) throws Exception;

    int changePwd(AccountForm pDTO) throws Exception;

    void deleteUser(AccountForm pDTO) throws Exception;

    AccountForm getFindId(AccountForm pDTO) throws Exception;

    void updateUserInfo(AccountForm pDTO) throws Exception;

    AccountForm showEmail(AccountForm pDTO) throws Exception;
}
