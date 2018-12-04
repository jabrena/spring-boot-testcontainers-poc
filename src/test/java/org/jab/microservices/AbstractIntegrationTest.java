package org.jab.microservices;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:3.0.6").withExposedPorts(6379);

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.initialization-mode: always",
                    "spring.datasource.postgres.driverClassName=" + postgresqlContainer.getDriverClassName(),
                    "spring.datasource.postgres.jdbc-url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.postgres.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.postgres.password=" + postgresqlContainer.getPassword(),

                    "cache.redis.host=" + redis.getContainerIpAddress(),
                    "cache.redis.port=" + redis.getMappedPort(6379),
                    "cache.redis.minIdl= 8",
                    "cache.redis.maxIdle= 500",
                    "cache.redis.maxActive= 2000",
                    "cache.redis.maxWait= 10000",
                    "cache.redis.timeOut= 0");
            values.applyTo(configurableApplicationContext);
        }
    }

}