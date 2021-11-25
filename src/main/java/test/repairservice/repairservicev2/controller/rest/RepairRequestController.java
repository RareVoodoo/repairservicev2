package test.repairservice.repairservicev2.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.repairservice.repairservicev2.dto.request.MasterAssignRequest;
import test.repairservice.repairservicev2.dto.request.RepairRequestCreationRequest;
import test.repairservice.repairservicev2.dto.request.RepairRequestStatusResponse;
import test.repairservice.repairservicev2.model.RepairRequest;
import test.repairservice.repairservicev2.repository.StatusRepository;
import test.repairservice.repairservicev2.service.impl.RepairRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/repair-request")
public class RepairRequestController {

    @Autowired
    private RepairRequestService repairRequestService;

    @Autowired
    StatusRepository statusRepository;

    @GetMapping(value = "/user-requests")
    public ResponseEntity<List<RepairRequest>> getCreatorRequests(@RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body(repairRequestService.getUserRepairRequests(username));
    }

    @GetMapping(value = "/requests")
    public ResponseEntity<List<RepairRequest>> getAllRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(repairRequestService.getAllRepairRequests());
    }

    @GetMapping(value = "/allRequests")
    public ResponseEntity<List<RepairRequest>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(repairRequestService.getAllRepairRequests());
    }

    @PostMapping()
    public ResponseEntity<String> createRepairRequest(@RequestBody RepairRequestCreationRequest repairRequest){
        repairRequestService.createRepairRequest(repairRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Request successfully has been created");
    }

    @PostMapping("/change-status")
    public ResponseEntity<String> changeRequestStatus(@RequestBody RepairRequestStatusResponse requestStatusResponse){
        repairRequestService.changeRepairRequestStatus(requestStatusResponse);
        return ResponseEntity.status(HttpStatus.OK).body("Repair request status has been changed");
    }

    @PostMapping("/assign-master")
    public ResponseEntity<String> assignMaster(@RequestBody MasterAssignRequest assignRequest){
        repairRequestService.assignRepairRequestMaster(assignRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Master successfully has been assigned");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRepairRequest(@RequestBody RepairRequest repairRequest){
        repairRequestService.removeRepairRequest(repairRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Request successfully has been deleted");
    }
}

