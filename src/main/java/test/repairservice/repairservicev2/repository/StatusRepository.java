package test.repairservice.repairservicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.repairservice.repairservicev2.model.RepairRequestStatus;
@Repository
public interface StatusRepository extends JpaRepository<RepairRequestStatus, Long> {
    public RepairRequestStatus getStatusByTitleEquals(String status);
}
