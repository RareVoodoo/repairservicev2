package test.repairservice.repairservicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.repairservice.repairservicev2.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
