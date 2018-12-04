package org.jab.microservices.service;

import org.jab.microservices.repository.Account;
import org.jab.microservices.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> getAcounts() {
        return repository.getAccounts();
    }

    @Override
    public Account getAccountById(int id) {
        return repository.getAccountById(id);
    }

}
