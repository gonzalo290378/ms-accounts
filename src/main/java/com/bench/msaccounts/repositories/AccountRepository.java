package com.bench.msaccounts.repositories;

import com.bench.msaccounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByAccountNumber(@Param("accountNumber") Long accountNumber);

    @Query("SELECT account " +
            "FROM Account account " +
            "ORDER BY account.accountNumber DESC " +
            "LIMIT 1"
            )
    public Optional<Account> findLastAccountNumber();

}

