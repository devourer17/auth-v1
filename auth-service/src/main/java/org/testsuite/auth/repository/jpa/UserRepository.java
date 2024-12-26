package org.testsuite.auth.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testsuite.auth.entities.jpa.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
