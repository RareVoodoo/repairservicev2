package test.repairservice.repairservicev2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import test.repairservice.repairservicev2.dto.request.UserUpdateRequest;
import test.repairservice.repairservicev2.model.Authority;
import test.repairservice.repairservicev2.model.Device;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.repository.DeviceRepository;
import test.repairservice.repairservicev2.repository.UserRepository;
import test.repairservice.repairservicev2.service.BaseAuthService;
import test.repairservice.repairservicev2.util.MasterDevice;
import test.repairservice.repairservicev2.util.UserAuthority;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DeviceRepository deviceRepository;


    @Transactional()
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameEquals(username).orElseThrow();
    }


    public List<User> getMasters() {
        return getUsersWithRole(UserAuthority.MASTER.getId());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private List<User> getUsersWithRole(Long roleId){
        return userRepository.findByAuthorityIdEquals(roleId);
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        User user = getUserByUsername(userUpdateRequest.getOldUsername());
        user.setUsername(userUpdateRequest.getUsername());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setFullName(userUpdateRequest.getFullName());

        if(userUpdateRequest.getDevices() != null){
            Set<Device> devices = new HashSet<>();
            for (String device: userUpdateRequest.getDevices()) {
                Device deviceObj = MasterDevice.identifyDevice(device);
                devices.add(deviceObj);
            }
            user.setMasterDevices(devices);
        }

        userRepository.save(user);
    }
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(UserUpdateRequest userUpdateRequest) {
        User user = getUserByUsername(userUpdateRequest.getUsername());
        userRepository.delete(user);
    }
}
