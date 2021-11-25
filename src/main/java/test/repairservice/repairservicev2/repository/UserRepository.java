package test.repairservice.repairservicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.repairservice.repairservicev2.model.User;

import java.util.List;
import java.util.Optional;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameEquals(String username);
    List<User> findByAuthorityIdEquals(Long roleId);
}
