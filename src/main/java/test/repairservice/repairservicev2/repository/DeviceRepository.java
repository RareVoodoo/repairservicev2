package test.repairservice.repairservicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.repairservice.repairservicev2.model.Device;
@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {

}
