package test.repairservice.repairservicev2.service.impl;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.repairservice.repairservicev2.dto.request.MasterAssignRequest;
import test.repairservice.repairservicev2.dto.request.RepairRequestCreationRequest;
import test.repairservice.repairservicev2.dto.request.RepairRequestStatusResponse;
import test.repairservice.repairservicev2.model.RepairRequest;

import test.repairservice.repairservicev2.model.RepairRequestStatus;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.repository.RepairRequestRepository;
import test.repairservice.repairservicev2.service.BaseAuthService;
import test.repairservice.repairservicev2.service.BaseRepairRequestService;
import test.repairservice.repairservicev2.util.MasterDevice;
import test.repairservice.repairservicev2.util.RequestStatus;


import java.time.Instant;
import java.util.List;

@Service
public class RepairRequestService implements BaseRepairRequestService {
    @Autowired
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    private UserService userService;

    private final String DEFAULT_NOT_ASSIGNED_USERNAME = "notassigned";

    @Transactional()
    @Override
    public List<RepairRequest> getUserRepairRequests(String username){
        User user = new User();
        user.setUsername(username);
        return repairRequestRepository.findAllByCreatorUsernameEquals(user.getUsername());
    }

    public List<RepairRequest> getAllRepairRequests() {
        return repairRequestRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createRepairRequest(RepairRequestCreationRequest creationRequest){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();

        RepairRequest repairRequest = RepairRequest.builder()
                .creationDate(Instant.now())
                .repairRequestStatus(new RepairRequestStatus(RequestStatus.ACCEPTED.getId(),RequestStatus.ACCEPTED.getTitle()))
                .creator(userService.getUserByUsername(user.getName()))
                .assignee(userService.getUserByUsername(DEFAULT_NOT_ASSIGNED_USERNAME))
                .device(MasterDevice.identifyDevice(creationRequest.getDeviceType()))
                .summary(creationRequest.getSummary())
                .description(creationRequest.getDescription())
                .build();

        repairRequestRepository.save(repairRequest);
    }

    @Override
    public List<RepairRequest> getMasterRequests(String username){
        return repairRequestRepository.findAllByAssigneeUsernameEquals(username);
    }

    @Override
    public List<RepairRequest> findAll(){
        return repairRequestRepository.findAll();
    }


    public void changeRepairRequestStatus(RepairRequestStatusResponse statusResponse) {
        RepairRequest repairRequest = repairRequestRepository.findById(statusResponse.getRequestId()).get();
        repairRequest.setRepairRequestStatus(new RepairRequestStatus(
                RequestStatus.identifyRequestStatus(statusResponse.getRequestStatus())));

        repairRequestRepository.save(repairRequest);
    }

    public void assignRepairRequestMaster(MasterAssignRequest masterAssignRequest) {
        User user = userService.getUserByUsername(masterAssignRequest.getAssigneeUsername());
        RepairRequest repairRequest = repairRequestRepository.findById(masterAssignRequest.getRequestId()).get();
        repairRequest.setAssignee(user);

        repairRequestRepository.save(repairRequest);
    }

    public void removeRepairRequest(RepairRequest repairRequest) {
        RepairRequest repairRequestRecord = repairRequestRepository.findById(repairRequest.getId()).get();

        repairRequestRepository.delete(repairRequestRecord);
    }

}
