package test.repairservice.repairservicev2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.repairservice.repairservicev2.model.Authority;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.repository.UserRepository;
import test.repairservice.repairservicev2.service.impl.UserService;

import java.util.Collections;

@Service
public class WebUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        GrantedAuthority authority = getUserAuthority(user);
        return buildUserEntityForAuthentication(user, authority);
    }

    private GrantedAuthority getUserAuthority(User user) {
        Authority authority = user.getAuthority();
        return new SimpleGrantedAuthority(authority.getTitle());
    }

    private org.springframework.security.core.userdetails.User buildUserEntityForAuthentication(User user, GrantedAuthority authority) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority));
    }


}
