package org.jab.microservices.controller;

import org.jab.microservices.repository.Account;
import org.jab.microservices.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAcounts();
    }

    @Cacheable(value = "post-single", key = "#id")
    @GetMapping("/accounts/{id}")
    public Account getPostByID(@PathVariable int id) {
        log.info("get account with id {}", id);
        return accountService.getAccountById(id);
    }


}