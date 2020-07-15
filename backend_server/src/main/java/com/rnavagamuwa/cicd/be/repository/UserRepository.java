package com.rnavagamuwa.cicd.be.repository;


import com.rnavagamuwa.cicd.be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rnavagamuwa
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySsn(String ssn);
}
