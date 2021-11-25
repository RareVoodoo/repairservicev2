package test.repairservice.repairservicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.repairservice.repairservicev2.model.RepairRequest;
import test.repairservice.repairservicev2.model.User;

import java.util.List;
@Repository
public interface RepairRequestRepository extends JpaRepository<RepairRequest,Long> {
    List<RepairRequest> findAllByCreatorUsernameEquals(String username);
    List<RepairRequest> findAllByAssigneeUsernameEquals(String username);
}
