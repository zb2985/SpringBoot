package kopo.jjh.prj.domain.repository;

import kopo.jjh.prj.security.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;
//User = Account
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUsername(String username);


    Account UsernameAndPassword(String username, String password);

    Account findUserByEmail(String email);

}