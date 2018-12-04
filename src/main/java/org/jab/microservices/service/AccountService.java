package org.jab.microservices.service;

import org.jab.microservices.repository.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAcounts();

    Account getAccountById(int id);
}
