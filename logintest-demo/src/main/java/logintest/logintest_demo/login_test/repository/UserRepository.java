package logintest.logintest_demo.login_test.repository;

import logintest.logintest_demo.login_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPassword(String password);
    boolean existsByEmail(String email);
}