package kopo.jjh.prj.domain.repository;

import kopo.jjh.prj.security.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

;

//User = Account

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {


    static void updateUserPassword(String username, String password) {
    }

    static Account findUserByUsername(String username) {

        return null;
    }

    Account findByUsername(String username);


    Account UsernameAndPassword(String username, String password);

   Account findUserByEmail(String email) ;


    Optional<Account> findByName(String name);
}