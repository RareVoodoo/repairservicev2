package test.repairservice.repairservicev2.service;

import test.repairservice.repairservicev2.dto.request.AuthRequest;
import test.repairservice.repairservicev2.model.User;

public interface BaseAuthService {
    void login(AuthRequest loginRequest);
    void registerUser(User user);
}
