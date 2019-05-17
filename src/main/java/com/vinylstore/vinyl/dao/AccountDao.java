package com.vinylstore.vinyl.dao;


import com.vinylstore.vinyl.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {
    @Query("select a from Account a where a.email = ?1")
    Account findByEmail(String email);
    Account findAccountById(Integer id);
}
