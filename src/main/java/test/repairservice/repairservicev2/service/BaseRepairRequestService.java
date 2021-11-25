package test.repairservice.repairservicev2.service;

import test.repairservice.repairservicev2.dto.request.RepairRequestCreationRequest;
import test.repairservice.repairservicev2.model.RepairRequest;

import java.util.List;

public interface BaseRepairRequestService {
    List<RepairRequest> getUserRepairRequests(String username);
    void createRepairRequest(RepairRequestCreationRequest repairRequest);
    List<RepairRequest> getMasterRequests(String username);
    List<RepairRequest> findAll();

}
