package com.wish.test.repository;

import com.wish.test.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

    // 사업자번호
    Account findByBusiNum(String busiNum);

    // 사업자번호 like
    @Query("SELECT m FROM Custom AS m WHERE m.busiNum LIKE %?1%")
    List<Object[]> findAllByBusiNumLike(String busiNum);

    Optional<Account> getByBusiNum(String busiNum);
}
