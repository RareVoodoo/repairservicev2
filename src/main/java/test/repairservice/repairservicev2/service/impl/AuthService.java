package test.repairservice.repairservicev2.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.repairservice.repairservicev2.dto.request.AuthRequest;
import test.repairservice.repairservicev2.model.Authority;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.repository.UserRepository;
import test.repairservice.repairservicev2.service.BaseAuthService;
import test.repairservice.repairservicev2.util.UserAuthority;

import javax.transaction.Transactional;

@Service
public class AuthService implements BaseAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void login(AuthRequest loginRequest){
        UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        authenticationManager.authenticate(credentials);
    }

    @Transactional()
    public void registerUser(User user){
        Authority authority = setClientAuthority();
        User newUser = User.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .password(passwordEncoder.encode(user.getPassword()))
                .phoneNumber(user.getPhoneNumber())
                .authority(authority)
                .build();

        userRepository.save(newUser);
    }

    private Authority setClientAuthority() {
        return Authority.builder()
                .id(UserAuthority.CLIENT.getId())
                .build();
    }
}
