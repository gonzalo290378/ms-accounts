package com.bench.msaccounts.repositories;

import com.bench.msaccounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findById(@Param("id") Long id);


}

