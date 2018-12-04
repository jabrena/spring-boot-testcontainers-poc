package org.jab.microservices.repository;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = AccountRepositoryTest.Initializer.class)
@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void getAccountTest() {
        List<Account> account = this.accountRepository.getAccounts();

        Assert.assertEquals(3, account.size());
    }

    @Test
    public void findById() {
        Account account = this.accountRepository.findById(1).get();

        Assert.assertEquals("user1", account.getUsername());
        Assert.assertEquals("user1@company.com", account.getEmail());
        System.out.println(account.getCreated());
    }

    @Test
    public void length() {
        int length = this.accountRepository.accountsLength();

        Assert.assertEquals(3, length);
    }

    @Test
    public void updateName() {
        boolean updated = this.accountRepository.updateName(3, "updatedname");
        Assert.assertTrue(updated);
    }

    @Test
    public void deleteList() {
        this.accountRepository.deleteIdList(Arrays.asList(1, 2));

        int length = this.accountRepository.accountsLength();

        Assert.assertEquals(1, length);
    }

    /**
     * This class replaces the embedded database with a PostgreSQL Docker Container
     */
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.initialization-mode: always",
                    "spring.datasource.postgres.driverClassName=" + postgresqlContainer.getDriverClassName(),
                    "spring.datasource.postgres.jdbc-url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.postgres.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.postgres.password=" + postgresqlContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}
