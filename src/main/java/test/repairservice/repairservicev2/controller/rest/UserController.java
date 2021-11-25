package test.repairservice.repairservicev2.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.repairservice.repairservicev2.dto.request.UserUpdateRequest;
import test.repairservice.repairservicev2.model.Device;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.repository.DeviceRepository;
import test.repairservice.repairservicev2.service.BaseAuthService;
import test.repairservice.repairservicev2.service.impl.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BaseAuthService authService;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/get-user")
    public ResponseEntity<User> getUser(@RequestParam(name="username") String username){
        Device device = deviceRepository.findById(1L).get();
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));

    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUser(userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User successfully has been updated");
    }

    @DeleteMapping
    public ResponseEntity<String> removeUser(@RequestBody UserUpdateRequest userUpdateRequest){
        userService.removeUser(userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User successfully has been deleted");
    }
}
