package org.jab.microservices.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT COUNT(*) FROM ACCOUNT")
    int accountsLength();

    @Modifying
    @Query("UPDATE ACCOUNT SET name = :name WHERE id = :id")
    boolean updateName(@Param("id") int id, @Param("name") String name);

    @Modifying
    @Query("DELETE FROM ACCOUNT WHERE id in (:ids)")
    void deleteIdList(@Param("ids") List<Integer> ids);

    @Query("SELECT * FROM ACCOUNT")
    List<Account> getAccounts();

    @Query("SELECT * FROM ACCOUNT WHERE id = (:id)")
    Account getAccountById(@Param("id") int id);

}
