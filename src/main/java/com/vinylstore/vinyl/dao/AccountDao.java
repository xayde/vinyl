package com.vinylstore.vinyl.dao;


import com.vinylstore.vinyl.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {

}
