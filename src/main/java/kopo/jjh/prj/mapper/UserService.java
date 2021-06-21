package kopo.jjh.prj.mapper;

import kopo.jjh.prj.security.dto.AccountForm;

public interface UserService {


    AccountForm findId(AccountForm vo);

    AccountForm findPassword(AccountForm vo);

    void updatePassword(AccountForm vo);

}